package io.booking.reservations

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.util.*

@SpringBootApplication
class ReservationServiceApplication

fun main(args: Array<String>) {
	runApplication<ReservationServiceApplication>(*args)
}

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

interface ReservationRepository: ReactiveCrudRepository<Reservation, UUID>

data class Reservation(val id: UUID = UUID.randomUUID(),
					   val reservedBy: String = "System",
					   val reservedFor: LocalDateTime = LocalDateTime.now())

@Component
class DatabaseSeeder(private val reservationRepository: ReservationRepository): ApplicationRunner {

	val logger: Logger = LoggerFactory.getLogger("DatabaseSeeder")

	override fun run(args: ApplicationArguments?) {
		val reservations = Flux.just(
				Reservation(reservedBy = "Karthik"),
				Reservation(reservedBy = "Aarthi"),
				Reservation(reservedBy = "Daksha")
		)
		reservationRepository.deleteAll()
							 .thenMany(reservationRepository.saveAll(reservations))
							 .thenMany(reservationRepository.findAll())
							 .doOnNext { reservation -> logger.debug(reservation.toString()) }
							 .subscribe()
	}

}