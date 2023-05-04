package org.jamr.jki.repository;

import java.util.List;
import org.jamr.jki.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepository extends JpaRepository<Share, Integer> {
	List<Share> findAllByShareeIDAndType(Integer shareeID, String type);
	List<Share> findAllByAccountID(Integer accountID);
	List<Share> deleteAllByAccountID(Integer accountID);
	List<Share> deleteAllBySubjectID(Integer subjectID);
	List<Share> deleteAllByShareeID(Integer shareeID);
	List<Share> deleteAllByType(String type);
	List<Share> deleteAllBySubjectIDAndType(Integer subjectID, String type);
}
