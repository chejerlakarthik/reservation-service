package io.booking.reservations.controller

import io.booking.reservations.model.Reservation
import io.booking.reservations.service.ReservationService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@RestController
class ReservationController(val reservationService: ReservationService) {

	@GetMapping("/reservations")
	fun listReservations(): Flux<Reservation> {
		return reservationService.getAllReservations()
	}

	@GetMapping("/reservations/{id}")
	fun findReservation(@PathVariable id: UUID) : Mono<Reservation> {
		return reservationService.getReservationById(id)
	}

	@PostMapping("/reservations")
	fun makeReservation(@RequestBody reservation: Reservation): Mono<Reservation> {
		return reservationService.addReservation(reservation)
	}
}