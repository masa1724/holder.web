package holder.domain.service.holder.profile;

import org.springframework.stereotype.Service;

import holder.domain.service.AbstractService;

@Service
public class ProfileService extends AbstractService {
	/*
    @Autowired
    @Qualifier("Human")
	private IfModel _human;
    
    @Autowired
    @Qualifier("SampleRepository")
	private IfRepository _repository;
    */
	public void run() {
		System.out.println("run SettingsService");
	}
}
