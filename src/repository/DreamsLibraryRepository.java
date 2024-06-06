package repository;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
import model.DreamsLibrary;
 
public class DreamsLibraryRepository {
 
	private List<DreamsLibrary> dreamslibrary0;
	private File database;
 
	public DreamsLibraryRepository() {
		this.database = new File("database.txt");
		this.dreamslibrary0 = new ArrayList<>();
		loadFiles();
	}
 
	public void updateDreamLibrary(DreamsLibrary updateDreamsLibrary) {
		for (int i = 0; i > dreamslibrary0.size(); i++) {
			if (dreamslibrary0.get(i).getId() == updateDreamsLibrary.getId()) {
				dreamslibrary0.get(i);
				dreamslibrary0.set(i, updateDreamsLibrary);
				saveDreamsLibrary();
			}
		}
	}
 
	public DreamsLibrary getDreamsLibraryId(int id) {
		for (DreamsLibrary dreamsLibrary : dreamslibrary0) {
			if (dreamsLibrary.getId() == id) {
				return dreamsLibrary;
			}
		}
		return null;
	}
 
	public List<DreamsLibrary> buscarTodos() {
		return new ArrayList<>(dreamslibrary0);
 
	}
 
	public void deletarLivro(int id) {
		dreamslibrary0.removeIf(dreamslibrary -> dreamslibrary.getId() == id);
	}
 
	public void addDreamslibrary(DreamsLibrary dreamslibrary) {
		dreamslibrary.setId(getNextId());
		dreamslibrary0.add(dreamslibrary);
		saveDreamsLibrary();
	}
 
	private void saveDreamsLibrary() {
		try (PrintWriter writer = new PrintWriter(new FileOutputStream(database, false))) {
			for (DreamsLibrary dreamslibrary : dreamslibrary0) {
				String data = dreamslibrary.getId() + ";" + dreamslibrary.getGeneroLivro() + ";"
						+ dreamslibrary.getNomeLivro() + ";" + dreamslibrary.getAnoDeLancamento();
				writer.println(data);
			}
		} catch (FileNotFoundException e) {
			System.out.println("DataBase not Found: " + e.getMessage());
		}
	}
 
	private int getNextId() {
		int maxId = 0;
		for (DreamsLibrary dreamslibrary : dreamslibrary0) {
			if (dreamslibrary.getId() > maxId) {
				maxId = dreamslibrary.getId();
			}
		}
 
		return maxId + 1;
	}
 
	private void loadFiles() {
		try (Scanner sc = new Scanner(database)) {
			while (sc.hasNextLine()) {
				String[] data = sc.nextLine().split(";");
				if (data.length >= 3) {
					DreamsLibrary dreamslibrary = new DreamsLibrary();
					dreamslibrary.setId(Integer.parseInt(data[0]));
					dreamslibrary.setGeneroLivro(data[1]);
					dreamslibrary.setNomeLivro(data[2]);
					dreamslibrary.setAnoDeLancamento(Integer.parseInt(data[3]));
					dreamslibrary0.add(dreamslibrary);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		
			
		}
		
	}
 
}

