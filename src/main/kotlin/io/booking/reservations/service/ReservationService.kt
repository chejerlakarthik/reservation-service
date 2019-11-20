package io.booking.reservations.service

import io.booking.reservations.model.Reservation
import io.booking.reservations.persistence.ReservationRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class ReservationService(private val reservationRepository: ReservationRepository) {

	fun getAllReservations(): Flux<Reservation> {
		return reservationRepository.findAll()
	}

    fun getReservationById(id: UUID): Mono<Reservation> {
		return reservationRepository.findById(id)
	}

    fun addReservation(reservation: Reservation): Mono<Reservation> {
		return reservationRepository.save(reservation)
	}

}