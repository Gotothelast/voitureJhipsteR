package fr.it_akademy_voiturejhipster.service;

import fr.it_akademy_voiturejhipster.service.dto.CarDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.it_akademy_voiturejhipster.domain.Car}.
 */
public interface CarService {
    /**
     * Save a car.
     *
     * @param carDTO the entity to save.
     * @return the persisted entity.
     */
    CarDTO save(CarDTO carDTO);

    /**
     * Updates a car.
     *
     * @param carDTO the entity to update.
     * @return the persisted entity.
     */
    CarDTO update(CarDTO carDTO);

    /**
     * Partially updates a car.
     *
     * @param carDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CarDTO> partialUpdate(CarDTO carDTO);

    /**
     * Get all the cars.
     *
     * @return the list of entities.
     */
    List<CarDTO> findAll();

    /**
     * Get the "id" car.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CarDTO> findOne(Long id);

    /**
     * Delete the "id" car.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
