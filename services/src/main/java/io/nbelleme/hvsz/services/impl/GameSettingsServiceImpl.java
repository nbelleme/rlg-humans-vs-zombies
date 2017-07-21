package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.common.Assert;
import io.nbelleme.hvsz.game.Game;
import io.nbelleme.hvsz.game.GameSettings;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.services.api.GameSettingsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static io.nbelleme.hvsz.common.Constants.GAME_ID;

@Service
final class GameSettingsServiceImpl implements GameSettingsService {

  private GameService gameService;

  /**
   * Constructor.
   *
   * @param gameService gameService
   */
  GameSettingsServiceImpl(GameService gameService) {
    this.gameService = Objects.requireNonNull(gameService);
  }

  @Override
  public void set(GameSettings gameSettings) {
    Game g = gameService.getCurrent();
    if (g == null) {
      g = new Game(GAME_ID);
    }
    g.setConfig(gameSettings);
    gameService.update(g);
  }

  @Override
  public GameSettings get() {
    Game active = gameService.getCurrent();
    Assert.gameDefined(active);
    return active.getConfig();
  }
}