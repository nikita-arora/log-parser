package in.goals.dbaccess.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.goals.dbaccess.model.ApiResponseDAO;

@Repository
public interface ApiResponseRepository extends JpaRepository<ApiResponseDAO, Integer> {

	ApiResponseDAO findByHttpMethodAndApiUrl(ApiResponseDAO.HttpMethod httpMethod, String apiUrl);
}
