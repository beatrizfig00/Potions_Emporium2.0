package Gui;

import Arquivos.ArquivoCliente;
import Exceptions.DadosInvalidosException;
import Negocio.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.List;

public class TelaCarrinhoController {

    @FXML
    private ListView<String> carrinhoListView;
    @FXML
    private Button botaoCaixa;
    @FXML
    private Button botaoVoltar;
    @FXML
    private Label totalLabel;

    private List<String> produtosNoCarrinho;

    @FXML
    public void initialize() {
        if (produtosNoCarrinho != null) {
            carrinhoListView.getItems().addAll(produtosNoCarrinho);
            atualizarTotal();
        } else {
            carrinhoListView.getItems().clear();
            totalLabel.setText("R$ 0,00");
        }
    }

    private void atualizarTotal() {
        double total = produtosNoCarrinho.stream()
                .mapToDouble(this::obterPrecoProduto)
                .sum();

        totalLabel.setText(String.format("R$ %.2f", total));
    }

    private double obterPrecoProduto(String produto) {
        return 10.0;
    }

    @FXML
    public void irParaCaixa(ActionEvent evento) {
        try {
            Parent telaCaixa = FXMLLoader.load(getClass().getResource("/com/potionsemporium/potions_emporium2/tela-caixa.fxml"));
            Scene cenaCaixa = new Scene(telaCaixa);
            Stage janela = (Stage) ((Node) evento.getSource()).getScene().getWindow();
            janela.setScene(cenaCaixa);
            janela.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro ao Ir para Caixa");
            alerta.setHeaderText(null);
            alerta.setContentText("Não foi possível carregar a tela de caixa.");
            alerta.showAndWait();
        }
    }

    @FXML
    public void voltar(ActionEvent evento) {
        try {
            Parent telaProdutos = FXMLLoader.load(getClass().getResource("/com/potionsemporium/potions_emporium2/tela-produtos.fxml"));
            Scene cenaProdutos = new Scene(telaProdutos);
            Stage janela = (Stage) ((Node) evento.getSource()).getScene().getWindow();
            janela.setScene(cenaProdutos);
            janela.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro ao Voltar");
            alerta.setHeaderText(null);
            alerta.setContentText("Não foi possível carregar a tela de produtos.");
            alerta.showAndWait();
        }
    }

    public void setProdutosNoCarrinho(List<String> produtos) {
        this.produtosNoCarrinho = produtos;
    }
}