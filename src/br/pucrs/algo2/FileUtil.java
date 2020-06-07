package br.pucrs.algo2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	private static List<String> readTestFile( String filename ) {
		List<String> list = new ArrayList<String>();
		
		try {
			String path = 
					System.getProperty( "user.dir" ) 
					+ File.separator + "in" 
							+ File.separator + filename;

//			System.out.println( path );
			
			// reads the file
			FileReader fileReader = new FileReader( new File( path ) ); 
			// creates a buffering character input stream
			BufferedReader br = new BufferedReader( fileReader ); 
			String line;
			
			while ( ( line = br.readLine() ) != null ) {
				if ( line.trim().length() > 0 ) {
					list.add( line );
				}
			}
			
			fileReader.close(); // closes the stream and release the resources

		} catch ( IOException e ) {
			e.printStackTrace();
		}
		
		return list;
	}
	
        public static int[][] loadMaze( String filename ) {
		List<String> lines = readTestFile( filename );
		
		int[][] maze = new int[ lines.size() ][ lines.get( 0 ).length() ];
		
		int i = 0;
		for( String line : lines ) {

			byte[] bytes = line.getBytes();
			int []  temp = new int[ bytes.length ];
			
			for( int j = 0; j < bytes.length; j++ ) {
				temp[ j ] = ( bytes[ j ] == 35 ) ? -1 : bytes[ j ];
				temp[ j ] = ( bytes[ j ] == 46 ) ?  0 : temp[ j ];
			}
			
			maze[ i ] = temp;
			i++;
		}
		
		return maze;
	}
	
//	public static void writeSolutionFile( String filename, List<Range> output ) {
//		try {
//			String path = 
//					System.getProperty( "user.dir" ) 
//					+ File.separator + "out" 
//							+ File.separator + filename;
//			
//			System.out.println( path );
//			
//			BufferedWriter bufferedWriter = 
//					new BufferedWriter( new FileWriter( path, false ) );
// 
//            for ( Range range : output ) {
//            	bufferedWriter.write( range.getStart() + "-" + range.getEnd() );
//                bufferedWriter.newLine();
//			}
// 
//            bufferedWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
// 	}
}
