.PHONY: all
all: build

.PHONY: up
up: assemble
	@docker-compose up --build -d

.PHONY: down
down:
	@docker-compose down

.PHONY: build-images
build-images: assemble
	@docker-compose build

.PHONY: build
build:
	@./gradlew clean build --warning-mode all

.PHONY: assemble
assemble:
	@./gradlew clean assemble --warning-mode all

.PHONY: run-tests
run-tests:
	@./gradlew test --warning-mode all

.PHONY: up-test
up-test:
	@docker-compose up -d mysql rabbitmq main

.PHONY: test
test: up-test
	@docker exec ship-routes-main ./gradlew test --warning-mode all

.PHONY: run
run:
	@./gradlew bootRun --args='$(args)'

.PHONY: ping-mysql
ping-mysql:
	@docker exec ship-routes-mysql mysqladmin --user=root --password= --host "127.0.0.1" ping --silent

.PHONY: restart
restart: restart-ships restart-ports restart-routes restart-backoffice

# SHIPS
.PHONY: build-image-ships
build-image-ships: assemble
	@docker-compose build ships
.PHONE: start-ships
start-ships: assemble
	@docker-compose up -d --build ships
.PHONY: restart-ships
restart-ships: assemble
	@docker-compose up -d --build --force-recreate --no-deps ships
.PHONY: run-ships
start-ships-dev:
	@./gradlew bootRun --args='ships server'

# PORTS
.PHONY: build-image-ports
build-image-ports: assemble
	@docker-compose build ports
.PHONE: start-ports
start-ports: assemble
	@docker-compose up -d --build ports
.PHONY: restart-ports
restart-ports: assemble
	@docker-compose up -d --build --force-recreate --no-deps ports
.PHONY: run-ports
start-ports-dev:
	@./gradlew bootRun --args='ports server'

# ROUTES
.PHONY: build-image-routes
build-image-routes: assemble
	@docker-compose build routes
.PHONE: start-routes
start-routes: assemble
	@docker-compose up -d --build routes
.PHONY: restart-routes
restart-routes: assemble
	@docker-compose up -d --build --force-recreate --no-deps routes
.PHONY: run-routes
start-routes-dev:
	@./gradlew bootRun --args='routes server'

# BACKOFFICE
.PHONY: build-image-backoffice
build-image-backoffice: assemble
	@docker-compose build backoffice
.PHONE: start-backoffice
start-backoffice: build-image-backoffice
	@docker-compose up -d --build backoffice
.PHONY: restart-backoffice
restart-backoffice: build-image-backoffice
	@docker-compose up -d --build --force-recreate --no-deps backoffice
.PHONY: run-backoffice
start-backoffice-dev:
	@./gradlew bootRun --args='backoffice server'
