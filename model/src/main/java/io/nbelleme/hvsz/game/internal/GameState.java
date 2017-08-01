package io.nbelleme.hvsz.game.internal;

/**
 * A game is ongoing when active or paused.
 * A game is over when not_started or victory
 */
public enum GameState {
  HUMAN_VICTORY,
  ZOMBIE_VICTORY,
  ACTIVE,
  NOT_STARTED,
  PAUSED,
  STOPPED
}
