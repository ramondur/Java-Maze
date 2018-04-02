package Entity;

import java.awt.Graphics;

import MazeGame.Game;
import Tiles.Tile;

/**
 * This is an abstract class that contains some behaviors associated with the different entities of the game.
 */

public abstract class Entity {
	
	protected Game game; //game object
	protected float x;  //x coordinate position in pixels
	protected float y; //y coordinate position in pixels
	
	/**
	 * It takes three variables: the game object, the x coordinate and the y coordinate. 
	 */
	public Entity(Game game, float x, float y){
		this.game = game;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Tick method.
	 */
	public abstract void tick();
	
	/**
	 * Render method.
	 */
	public abstract void render(Graphics g);
	
	/**
	 * Checks if the tile in the coordinates given in solid.
	 */
	public boolean collisionWithTile(int y, int x) {
		return Tile.getTile(x,y).isSolid();
	}
	
	/**
	 * Moves the entity up two pixels unless the entity collides with a tile (the tile is solid).
	 * It checks both upper corners of the entity image.
	 */
	public void moveUp() {
		int ty = (int) (y-2) / (Tile.TILE_WIDTH);
		if(!collisionWithTile((int) (x) / Tile.TILE_WIDTH, ty) &&
				!collisionWithTile((int) ( x + Tile.TILE_WIDTH -3) / Tile.TILE_WIDTH, ty))
			y -= 2;
	}
	
	/**
	 * Moves the entity down two pixels unless the entity collides with a tile (the tile is solid).
	 * It checks both lower corners of the entity image.
	 */
	public void moveDown() {
		int ty = (int) (y-2+Tile.TILE_WIDTH) / (Tile.TILE_WIDTH);
		if(!collisionWithTile((int) (x) / Tile.TILE_WIDTH, ty) &&
				!collisionWithTile((int) (x + Tile.TILE_WIDTH -3) / Tile.TILE_WIDTH, ty))
			y += 2;
	}
	
	/**
	 * Moves the entity left two pixels unless the entity collides with a tile (the tile is solid).
	 * It checks both corners on the left of the entity image.
	 */
	public void moveLeft() {
		int tx = (int) (x-2) / (Tile.TILE_WIDTH);
		if(!collisionWithTile(tx,(int) y / Tile.TILE_WIDTH) &&
				!collisionWithTile(tx, (int) (y + Tile.TILE_WIDTH-3) / Tile.TILE_HEIGHT))
			x -= 2;
	}
	
	/**
	 * Moves the entity right two pixels unless the entity collides with a tile (the tile is solid).
	 * It checks both corners on the right of the entity image.
	 */
	public void moveRight() {
		int tx = (int) (x-2+Tile.TILE_WIDTH) / (Tile.TILE_WIDTH);
		if(!collisionWithTile(tx, (int) y / Tile.TILE_WIDTH) &&  
				!collisionWithTile(tx, (int) (y + Tile.TILE_WIDTH-3) / Tile.TILE_HEIGHT))
			x += 2;
	}
}
