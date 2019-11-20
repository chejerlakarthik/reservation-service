package io.booking.reservations.persistence

import io.booking.reservations.model.Reservation
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.*

interface ReservationRepository: ReactiveCrudRepository<Reservation, UUID>