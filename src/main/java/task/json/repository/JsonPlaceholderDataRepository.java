package task.json.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import task.json.model.JsonPlaceholderData;

public interface JsonPlaceholderDataRepository extends JpaRepository<JsonPlaceholderData, Long> {

}
