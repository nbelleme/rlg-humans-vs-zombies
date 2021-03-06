package io.resourcepool.hvsz.game;


import io.resourcepool.hvsz.common.Assert;
import io.resourcepool.hvsz.common.models.GenericBuilder;
import io.resourcepool.hvsz.humans.SafeZone;
import io.resourcepool.hvsz.persistence.dao.DaoMapDb;
import io.resourcepool.hvsz.supply.FoodSupply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static io.resourcepool.hvsz.common.Constants.GAME_ID;

@Service
public class GameServiceImpl implements GameService {

  private static final long SECONDS_IN_ONE_MINUTE = 60;

  @Autowired
  DaoMapDb dao;

  @Autowired
  GameSettingsService settingsService;

  @Override
  public Game get() {
    return dao.get(GAME_ID);
  }

  @Override
  public void startGame() {
    //Asserts that the game is over if it exists
    Game currentGame = get();
    if (currentGame != null) {
      Assert.gameOver(currentGame);
    }

    Game game = new Game();
    // Retrieve settings
    GameSettings conf = settingsService.get();
    game.setConfig(conf);
    // Init game status
    Status status = GenericBuilder.of(Status::new)
      .with(Status::setRemainingHumanTickets, conf.getHumanTickets())
      .with(Status::setCurrentHumansOnField, 0)
      .with(Status::setRemainingTime, conf.getGameDuration() * SECONDS_IN_ONE_MINUTE)
      .with(Status::setMaxHumansOnField, conf.getMaxHumansOnField())
      .with(Status::setGameState, GameState.ACTIVE)
      .build();
    game.setStatus(status);
    // Init game supply zones
    List<FoodSupply> foodSupplies = new ArrayList<>(conf.getNbFoodSupplyZones());
    int foodPerZone = conf.getNbFoodSupplies() / conf.getNbFoodSupplyZones();
    for (int i = 0; i < conf.getNbFoodSupplyZones(); i++) {
      foodSupplies.add(new FoodSupply((long) i, foodPerZone, foodPerZone));
    }
    game.setFoodSupplies(foodSupplies);
    // Init game safe zones
    List<SafeZone> safeZones = new ArrayList<>(conf.getNbSafeZones());
    int nbSafeZones = conf.getNbSafeZones();
    for (int i = 0; i < nbSafeZones; i++) {
      safeZones.add(new SafeZone((long) i, conf.getStartingSafeZoneSupplies(), 100));
    }
    game.setSafeZones(safeZones);
    // Save game
    update(game);
  }

  @Override
  public void pauseGame() {
    Game game = dao.get(GAME_ID);
    Assert.gameActive(game);
    game.getStatus().setGameState(GameState.PAUSED);
    update(game);
  }

  @Override
  public void resumeGame() {
    Game game = dao.get(GAME_ID);
    Assert.gamePaused(game);
    game.getStatus().setGameState(GameState.ACTIVE);
    update(game);
  }

  @Override
  public void stopGame() {
    Game game = dao.get(GAME_ID);
    Assert.gameOngoing(game);
    game.getStatus().setGameState(GameState.ZOMBIE_VICTORY);
    update(game);
  }

  @Override
  public void update(Game g) {
    Assert.gameDefined(g);
    if (allSafeZonesDestroyed(g) || noHumanLivesLeft(g)) {
      g.getStatus().setGameState(GameState.ZOMBIE_VICTORY);
    } else if (timesUp(g)) {
      g.getStatus().setGameState(GameState.HUMAN_VICTORY);
    }
    dao.set(GAME_ID, g);
  }

  /**
   * @param g the active game
   * @return true if the time is up
   */
  private boolean timesUp(Game g) {
    return g.getStatus().getRemainingTime() <= 0;
  }

  /**
   * @param g the active game
   * @return true if all human lives have been consumed
   */
  private boolean noHumanLivesLeft(Game g) {
    return g.getStatus().getRemainingHumanTickets() + g.getStatus().getCurrentHumansOnField() <= 0;
  }

  /**
   * @param g the active game
   * @return true if all safe zones were destroyed
   */
  private boolean allSafeZonesDestroyed(Game g) {
    return g.getSafeZones() == null ? true : g.getSafeZones().stream().filter(z -> z.getLevel() > 0).count() == 0;
  }

}
