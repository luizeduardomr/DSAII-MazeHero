package br.pucrs.algo2;

import java.util.ArrayList;
import java.util.List;

public class Solver {
	
	public static void main(String[] args) {
		
		String[] casosTeste = new String[] {
				"caso0_cohen.txt",
				"caso1_cohen.txt",
				"caso2_cohen.txt",
				"caso3_cohen.txt",
				"caso4_cohen.txt",
				"caso5_cohen.txt",
				"caso6_cohen.txt",
				"caso7_cohen.txt",
		};
		
		for( int i = 0; i < casosTeste.length; i++ ) {
			int[][] maze = FileUtil.loadMaze( casosTeste[ i ] );
			
//			printMaze( maze );
			
			Position hero    = lookup( maze, ( byte )"A".charAt( 0 ) );
			Position villain = lookup( maze, ( byte )"B".charAt( 0 ) );
			
//			dump( "Hero", hero );
//			dump( "Villain", villain );
			
			solve( maze, villain );
			
			System.out.println( casosTeste[ i ] + " - Menor distância entre A e B: " + maze[ hero.y ][ hero.x ] );
			
		}
		
	}
	
	private static void solve(int[][] maze, Position p) {
		/*
		 * Marca o labirinto com a distância mínima para chegar a cada posição a partir
		 * da posição p
		 */

			List<Position> fila = new ArrayList<>();

		Position q;

		initMaze( maze );

		/* Começa da posição p */
		maze[ p.y ][ p.x ] = 0;
		fila.add( p );

		while (!fila.isEmpty()) {
			q = fila.remove( 0 );
			calculateDistanceFromVillain( maze, q, fila );
		}
		
//		printMaze( maze );
	}

	static void calculateDistanceFromVillain(int[][] maze, Position p, List lista) {
		/*
		 * Marca cada um dos vizinhos da posição p que ainda é livre com um a mais que o
		 * rótulo da posição p.
		 */		
		
		if (maze[p.y][p.x + 1] == -2) {
			/* Se a posição é livre e não está marcada, marca e insere no fim da fila. */
			maze[p.y][p.x + 1] = maze[p.y][p.x] + 1;
			
			Position aux = new Position();
			aux.x = p.x + 1;
			aux.y = p.y;
			lista.add(aux);
		}

		if (maze[p.y][p.x - 1] == -2) {
			maze[p.y][p.x - 1] =	 maze[p.y][p.x] + 1;
			
			Position aux = new Position();
			aux.x = p.x - 1;
			aux.y = p.y;
			lista.add(aux);
		}

		if (maze[p.y + 1][p.x] == -2) {
			maze[p.y + 1][p.x] = maze[p.y][p.x] + 1;
			
			Position aux = new Position();
			aux.x = p.x;
			aux.y = p.y + 1;
			lista.add(aux);
		}

		if (maze[p.y - 1][p.x] == -2) {
			maze[p.y - 1][p.x] = maze[p.y][p.x] + 1;
			
			Position aux = new Position();
			aux.x = p.x;
			aux.y = p.y - 1;
			lista.add(aux);
		}
	}

	static void initMaze( int[][] maze ) {
		/*
		 * Coloca -2 em todas as posições livre, significando que nenhuma delas está
		 * marcada.
		 */
		for (int i = 0; i < maze.length; i++)
			for (int j = 0; j < maze[i].length; j++)
				if (maze[i][j] != -1 )
					maze[i][j] = -2;
		
//		printMaze( maze );
	}
	
	public static void dump( String role, Position p ) {
		System.out.println( role + " " + p.toString( ) );
	}
	
	private static void printMaze( int[][] maze ) {
		for( int i = 0; i < maze.length; i++ ) {
			for( int j = 0; j < maze[i].length; j++ ) {
				System.out.print( maze[i][j]  + " " );
			}
			System.out.println();
		}
	}
	
	// Procura o ator (hero (A) or villain (B))
	private static Position lookup( int[][] maze, byte actor ) {
		for( int i = 0; i < maze.length; i++ ) {
			for( int j = 0; j < maze[i].length; j++ ) {
				if ( maze[i][j] == actor ) {
					Position p = new Position();
					p.x = j;
					p.y = i;
					return p;
				}
			}
		}
		
		return null;
	}
	
}
