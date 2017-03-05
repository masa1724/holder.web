package holder.domain.service.holder;

import org.springframework.stereotype.Service;

import holder.app.utils.message.MessageId;
import holder.domain.service.AbstractService;

@Service
public class HomeService extends AbstractService {
	/*
    @Autowired
    @Qualifier("Human")
	private IfModel _human;
    
    @Autowired
    @Qualifier("SampleRepository")
	private IfRepository _repository;
    */
	public void run() {
		System.out.println("run homeserive");
	}
}
