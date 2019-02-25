package task.json.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


// encja stworzona z odpowiednimi polami do mapowania postow w formacie Json
@Entity
@Getter
@Setter
public class JsonPlaceholderData {

    private Long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String title;

    @Column(length = 2000)
    private String body;

    public JsonPlaceholderData(Long userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public JsonPlaceholderData() {
    }

    // nadpisanie metody strong zeby wywolana w kontrolerze zapisywala posty w formacie json
    @Override
    public String toString() {
        return "JsonPlaceholderData{" + "\n" +
                 " userId " + " : " + userId+ "\n"+
                 " id" + " : " + id + "\n"+
                " title"+ " : " + "\""+ title  + "\""+"\n"+
                " body" + " : " + "\""+body+ "\""+ "\n" +
                '}';
    }
}
