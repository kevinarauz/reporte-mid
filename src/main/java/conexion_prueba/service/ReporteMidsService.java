package conexion_prueba.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import conexion_prueba.repository.RepositoryReporteMids;
@Service
public class ReporteMidsService {
	@Autowired
	RepositoryReporteMids	repositoryReporteMids;
	
	public List<Map<String, Object>> getJm() {
       
        List<Map<String, Object>> registros = null;
        
        registros = repositoryReporteMids.getJm();
    
        
        return registros != null ? registros : new ArrayList<>();
    }
	
	
	public List<Map<String, Object>> consultaMidActivosActualizados() {
	       
        List<Map<String, Object>> registros = null;
        
        String usuario="JMUNOZ";
        
        registros = repositoryReporteMids.consultaMidActivosActualizados( usuario );
    
        
        return registros != null ? registros : new ArrayList<>();
    }
	
	

}
