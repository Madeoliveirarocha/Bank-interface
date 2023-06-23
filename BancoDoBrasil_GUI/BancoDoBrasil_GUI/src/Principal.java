import br.univali.cc.prog3.banco.dominio.Banco;
import br.univali.cc.prog3.banco.visao.CaixaEletronicoGUI;

public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Banco bb = new Banco("Banco do Brasil", 1);
        // caixa eletrônico do tipo console
        //CaixaEletronicoConsole caixa= new CaixaEletronicoConsole(bb);
        
        // caixa eletrônico do tipo GUI
        CaixaEletronicoGUI caixa = new CaixaEletronicoGUI(bb);
        
        caixa.menu();
    }
}
