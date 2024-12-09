package ifrn.pf.projeto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pf.projeto.models.Convidado;
import ifrn.pf.projeto.models.Projeto;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long>{
	
	List<Convidado> findByProjeto(Projeto projeto);

}
