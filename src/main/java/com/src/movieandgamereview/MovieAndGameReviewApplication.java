package com.src.movieandgamereview;

import com.src.movieandgamereview.group.UserGroups;
import com.src.movieandgamereview.model.*;
import com.src.movieandgamereview.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDate;


@SpringBootApplication
public class MovieAndGameReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieAndGameReviewApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository user, UserGroupRepository userGroup,
										RateRepository rate, PersonRepository person,
										GameGenreRepository gameGenre, MovieGenreRepository movieGenre,
										DirectorRepository director, ActorRepository actor,
										LanguageRepository language, InformationRepository information,
										MovieRepository movie, GameRepository game,
										ReviewRepository review) {
		return args -> {
			LocalDate birthDate = LocalDate.of(1992, 1, 1);
			LocalDate currentDate = LocalDate.now();

			AggregateReference<UserGroup, Long> testUserGroup = AggregateReference.to(userGroup.save(new UserGroup(UserGroups.MEMBER)).getId());

			User baseUser = new User("firstName", "lastName",
					"email@email.com", "password", birthDate, currentDate, testUserGroup);

			AggregateReference<User, Long> testUser = AggregateReference.to(user.save(baseUser).getId());

			AggregateReference<Rate, Long> testRate = AggregateReference.to(
					rate.save(new Rate("testRate")).getId());

			AggregateReference<Person, Long> testPerson = AggregateReference.to(person.save
					(new Person("firstName", "lastName", birthDate)).getId());

			AggregateReference<GameGenre, Long> testGameGenre = AggregateReference.to(gameGenre.save(
					new GameGenre("testGameGenre")).getId());
			AggregateReference<MovieGenre, Long> testMovieGenre = AggregateReference.to(movieGenre.save(
					new MovieGenre("testMovieGenre")).getId());

			AggregateReference<Director, Long> testDirector = AggregateReference.to(director.save(
					new Director(testPerson)).getId());

			Actor testActor = new Actor(testPerson);
			actor.save(testActor);

			AggregateReference<Language, Long> testLanguage = AggregateReference.to(language.save(
					new Language("testLanguage")).getId());

			AggregateReference<Information, Long> testInformation = AggregateReference.to(information.save(
					new Information("testTitle", "testDescription", currentDate, 10000,
							testRate, testLanguage, testDirector)).getId());
			AggregateReference<Information, Long> newTestInformation = AggregateReference.to(information.save(
					new Information("testTitle", "testDescription", currentDate, 10000,
							testRate, testLanguage, testDirector)).getId());
			AggregateReference<Movie, Long> testMovie = AggregateReference.to(movie.save(
					new Movie(66, testInformation, testMovieGenre)).getId());
			AggregateReference<Game, Long> testGame = AggregateReference.to(game.save(
					new Game(newTestInformation, testGameGenre)).getId());
			Review testReview = new Review("testDescription", testUser, testMovie, null);
			Review newTestReview = new Review("testDescription", testUser, null, testGame);
			review.save(testReview);
			review.save(newTestReview);

			/*Optional<Information> testFindInformation = information.findById(Objects.requireNonNull(testInformation.getId()));
			Optional<Information> newTestFindInformation = information.findById(Objects.requireNonNull(newTestInformation.getId()));
			Optional<Actor> findTestActor = actor.findById(Objects.requireNonNull(testActor.getId()));
			Optional<Director> findTestDirector = director.findById(Objects.requireNonNull(testDirector.getId()));
			Optional<User> findTestUser = user.findById(Objects.requireNonNull(testUser.getId()));
			Optional<UserGroup> findTestUserGroup = userGroup.findById(Objects.requireNonNull(testUserGroup.getId()));
			Optional<Rate> findTestRate = rate.findById(Objects.requireNonNull(testRate.getId()));
			Optional<MovieGenre> findTestMovieGenre = movieGenre.findById(Objects.requireNonNull(testMovieGenre.getId()));
			Optional<GameGenre> findTestGameGenre = gameGenre.findById(Objects.requireNonNull(testGameGenre.getId()));
			Optional<Movie> findTestMovie = movie.findById(Objects.requireNonNull(testMovie.getId()));
			Optional<Game> findTestGame = game.findById(Objects.requireNonNull(testGame.getId()));
            testFindInformation.ifPresent(value -> {
				value.getReviews().add(testReview);
				value.getActors().add(testActor);
				information.save(value);
			});
            newTestFindInformation.ifPresent(value -> {
				value.getReviews().add(newTestReview);
				information.save(value);
			});
			findTestActor.ifPresent(value -> {
				value.getMovies().add(findTestMovie.get());
				actor.save(value);
			});
			findTestDirector.ifPresent(value -> {
				value.getMovies().add(findTestMovie.get());
				director.save(value);
			});
			findTestUser.ifPresent(value -> {
				value.getReviews().add(testReview);
				user.save(value);
			});
			findTestUserGroup.ifPresent(value -> {
				value.getUsers().add(findTestUser.get());
				userGroup.save(value);
			});
			findTestRate.ifPresent(value -> {
				value.getMovies().add(findTestMovie.get());
				value.getGames().add(findTestGame.get());
				rate.save(value);
			});
			findTestMovieGenre.ifPresent(value -> {
				value.getMovies().add(findTestMovie.get());
				movieGenre.save(value);
			});
			findTestGameGenre.ifPresent(value -> {
				value.getGames().add(findTestGame.get());
				gameGenre.save(value);
			});
			 */
		};
	}
}
