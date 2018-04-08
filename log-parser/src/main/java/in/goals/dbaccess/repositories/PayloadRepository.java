package in.goals.dbaccess.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.goals.dbaccess.model.PayloadDAO;

@Repository
public interface PayloadRepository extends JpaRepository<PayloadDAO, Integer> {

	PayloadDAO findByWaybill(String waybill);

}
