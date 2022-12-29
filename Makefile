.PHONY: all
all: build

.PHONY: up
up:
	@docker-compose up -d

.PHONY: build
build:
	@./gradlew build --warning-mode all

.PHONY: run-tests
run-tests:
	@./gradlew test --warning-mode all

.PHONY: test
test:
	@docker exec ship-routes-java ./gradlew test --warning-mode all

.PHONY: run
run:
	@./gradlew bootRun --args='$(args)'

.PHONY: ping-mysql
ping-mysql:
	@docker exec ship-routes-mysql mysqladmin --user=root --password= --host "127.0.0.1" ping --silent

# Start the app
.PHONY: start-ship
start-ship:
	@./gradlew bootRun --args='ship server'
