package calculator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HTMLVillages {
    private Owner owner;
    private String login;
    private String urlAdress = "https://pl113.plemiona.pl/guest.php?name=";
    private String urlOwner = "https://pl113.plemiona.pl/guest.php?screen=info_player&id=";
    private String id;

    public HTMLVillages(Owner owner){
        this.owner = owner;
        login = owner.getName();
    }

    public void setUrlOwner(){;
        urlAdress = urlAdress + login.replace(' ', '+');
        System.out.println(urlAdress);
        try {
            Document doc = Jsoup.connect(urlAdress).get();
            Elements trs = doc.select("#player_ranking_table").select("tr");
            Element idTr = trs.get(1);
            Elements links = idTr.select("a[href]");

            for(Element l : links){
                if(l.text().equalsIgnoreCase(login)){
                    id = l.attr("abs:href");
                }
            }
            String splitIdLink [] = id.split("=");
            id = splitIdLink[splitIdLink.length-1];

            urlOwner = urlOwner + id;

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void setVillageInfo(){
        String coord = "";
        String name = "";
        try {
            Document doc = Jsoup.connect(urlOwner).get();
            String q = "#villages_list.vis > tbody > tr > td";
            Elements trs = doc.select(q);

            for(int i=0; i<trs.size(); ++i){
                name = trs.get(i).text();
                coord = trs.get(i+1).text();
                name = name + " (" + coord + ")";
                i=i+2;
                owner.addVillage(new Village(name, coord));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prepareVillageInfo(){
        setUrlOwner();
        setVillageInfo();
    }
}
