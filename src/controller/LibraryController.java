package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DreamsLibrary;
import repository.DreamsLibraryRepository;

public class LibraryController {

	@FXML
	private TextField nomeLivro;

	@FXML
	private TextField generoLivro;

	@FXML
	private TextField anoDeLancamento;

	@FXML
	private TableView<DreamsLibrary> tableView;

	@FXML
	private TableColumn<DreamsLibrary, String> colNomeLivro;

	@FXML
	private TableColumn<DreamsLibrary, String> colGeneroLivro;

	@FXML
	private TableColumn<DreamsLibrary, Integer> colAnoLancamento;

	private DreamsLibraryRepository repoLibrary;
	private ObservableList<DreamsLibrary> data;

	@FXML
	public void initialize() {
		colNomeLivro.setCellValueFactory(new PropertyValueFactory<>("nomeLivro"));
		colGeneroLivro.setCellValueFactory(new PropertyValueFactory<>("generoLivro"));
		colAnoLancamento.setCellValueFactory(new PropertyValueFactory<>("anoDeLancamento"));
		data = FXCollections.observableArrayList();
		repoLibrary = new DreamsLibraryRepository();
		carregarDadosSalvos();
	}

	public void carregarDadosSalvos() {
		try (BufferedReader br = new BufferedReader(new FileReader("database.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				if (parts.length >= 4) {
					DreamsLibrary book = new DreamsLibrary();
					book.setId(Integer.parseInt(parts[0]));
					book.setNomeLivro(parts[1]);
					book.setGeneroLivro(parts[2]);
					book.setAnoDeLancamento(Integer.parseInt(parts[3]));
					data.add(book);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Adicionar() {
		DreamsLibrary book = new DreamsLibrary();
		book.setNomeLivro(nomeLivro.getText());
		book.setGeneroLivro(generoLivro.getText());
		book.setAnoDeLancamento(Integer.parseInt(anoDeLancamento.getText()));
		repoLibrary.addDreamslibrary(book);
	}

	public void clearFields() {
		nomeLivro.clear();
		generoLivro.clear();
		anoDeLancamento.clear();
	}

	public void limpar() {
		System.out.println("limpou");
		clearFields();
	}

}
