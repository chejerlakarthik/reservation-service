package io.booking.reservations.persistence

import io.booking.reservations.model.Reservation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

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