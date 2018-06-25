package br.edu.ufsj.minerva.control;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import br.edu.ufsj.minerva.R;
import br.edu.ufsj.minerva.model.Candidado;

/**
 * Created by Iago Alvarenga on 25/05/2018.
 */

public class XML {
    public void readCandidatos(Context context) {
        ArrayList<Candidado> candidatos = new ArrayList<Candidado>();
        try {
            InputStream is = context.getResources().openRawResource(R.raw.candidatos);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("candidato");

            for (int i=0; i<nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    Candidado candidato = new Candidado(new Random().nextInt(),
                            Integer.valueOf(getValue("id", element2)),
                            getValue("nome", element2),
                            getValue("partido", element2),
                            Integer.valueOf(getValue("numero", element2)),
                            getValue("foto", element2));
                    new Candidado().getCandidatos().add(candidato);
                    Log.i("Leitura de cadidatos",candidato.toString());
                }
            }

        } catch (Exception e) {e.printStackTrace();}

    }
    public byte[] readkey(Context context) {
        byte[] publicKey = new byte[32];
        try {
            InputStream is = context.getResources().openRawResource(R.raw.key_public_assinatura);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("publickey");

            for (int i=0; i<nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;

                    publicKey[Integer.valueOf(getValue("postion", element2))] = Byte.valueOf(getValue("key", element2));
                    Log.i("carregar: ", String.valueOf(getValue("postion", element2)));

                    Log.i("Chave Carregada: ", publicKey.toString());
                }
            }

        } catch (Exception e) {e.printStackTrace();}
        return publicKey;
    }
    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }


    private static OutputStream fos;
    private static XmlSerializer serializer = Xml.newSerializer();
    public void inicializeWriteVoto(Context context) {
        String Filename = "votos.xml";
        fos = null;
        try {
            fos = context.openFileOutput(Filename, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            serializer.setOutput(fos, "UTF-8");
            serializer.startDocument(null, null);
            serializer.startTag("", "votos");
            serializer.flush();
            Log.i("Arquivo de votos inicializados","Sucesso");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveWriteVoto(int idCandidato) {
        Candidado candidatoFinal = null;
        for (Candidado canditado :  new Candidado().getCandidatos()) {
            if(canditado.getIdRandom() == idCandidato ){
                candidatoFinal = canditado;
            }
        }  if(idCandidato == 0){
            Candidado branco = new Candidado(0,0,"Branco", "", 0,"branco");
            candidatoFinal = branco;
        }else if(idCandidato == -1){
            Candidado nulo = new Candidado(-1,-1,"Nulo", "", -1,"nulo");
            candidatoFinal = nulo;
        }
        try {
            serializer.startTag("", "voto");
            serializer.startTag("", "idCandidato");
            serializer.text(String.valueOf(candidatoFinal.getNumero()));
            serializer.endTag("", "idCandidato");
            serializer.endTag("", "voto");
            serializer.flush();
            Log.i("Arquivo de votos salvos","Sucesso");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void finalizeWriteVoto() {
        if( new Candidado().getCandidatos().size() > 0) {
            try {
                serializer.endTag("", "votos");
                serializer.endDocument();
                serializer.flush();
                fos.close();

                //Apaga os canddidatos da memoria ao finalizar
                new Candidado().getCandidatos().clear();
                Log.i("Arquivo de votos finalizado", "Sucesso");
            } catch (Exception e) {
                throw new RuntimeException(e);

            }
        }
    }

    public ArrayList<Candidado>  readVotos(Context context) {
        String Filename = "votos.xml";
        ArrayList<Candidado> candidatos = new ArrayList<Candidado>();
        try {
            //FileInputStream is = new FileInputStream(Filename);
            FileInputStream is = context.openFileInput(Filename);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("voto");

            for (int i=0; i<nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    int numero = Integer.valueOf(getValue("idCandidato", element2));
                    for (Candidado canditado :  new Candidado().getCandidatos()){

                        if(canditado.getNumero() == numero ){
                            candidatos.add(canditado);
                        }
                    } if( numero == 0){
                        Candidado branco = new Candidado(0,0,"Branco", "", 0,"branco");
                        candidatos.add(branco);
                    }else if( numero == -1){
                        Candidado nulo = new Candidado(-1,-1,"Nulo", "", -1,"nulo");
                        candidatos.add(nulo);
                    }
                    Log.i("Leitura de votos cadidatos",candidatos.toString());
                }
            }

        } catch (Exception e) {e.printStackTrace();
            Log.i("ERRO na Leitura de votos cadidatos, eleição nao terminada",candidatos.toString());
        }
        return candidatos;
    }
}
