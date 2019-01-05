package cz.jpalcut.pia.service;

import cz.jpalcut.pia.dao.StateDAO;
import cz.jpalcut.pia.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Služba pro správu států
 */
@Service
@Transactional
public class StateService {

    @Autowired
    StateDAO stateDAO;

    /**
     * Vrátí seznam všech států
     *
     * @return seznam států
     */
    public List<State> getAllStates() {
        return stateDAO.findAll();
    }

}
