package ma.exovate.medaibe.repositories;

import ma.exovate.medaibe.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepo extends JpaRepository<Report, Long> {
}
