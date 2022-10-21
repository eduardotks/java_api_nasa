import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class GeradorDeFigurinhas {
    public void cria(InputStream inputStream, String NomeArquivo ) throws IOException {
        //por link
        //URL inputS = new URL("https://img.freepik.com/vetores-gratis/fundo-azul-favo-de-mel-tecnologico_52683-22308.jpg");
        //por diretório
        //BufferedImage imagemOriginal = ImageIO.read(new File("entrada/teste1.jpg"));
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int NovaAltura = altura + 100;
        BufferedImage novaImagem = new BufferedImage(largura, NovaAltura, BufferedImage.TRANSLUCENT);
        Graphics2D graph = (Graphics2D) novaImagem.getGraphics();
        graph.drawImage(imagemOriginal, 0, 0, null);
        //--- altera fonte
        Font fonts = new Font(Font.SANS_SERIF, Font.BOLD, 50);
        graph.setColor(Color.ORANGE);
        graph.setFont(fonts);
        graph.drawString(NomeArquivo, 145, NovaAltura - 70 );
        //---
        //local final
        var nomeArquivoFiltrado = NomeArquivo.replaceAll("[^a-zA-Z0-9 ]", "");
        ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivoFiltrado + ".png"));
    }

    //para rodar basta executar esta função main e observar a pasta saída.
    //public static void main(String[] args) throws IOException {
    //var geradora =  new GeradorDeFigurinhas();
    //geradora.cria();
    //}
}
