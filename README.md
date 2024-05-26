# Процедура запуска автотестов
1. Клонировать текущий проект при помощи команды "git clone"
2. Открыть проект в IntelliJ IDEA.
3. Запустить Docker, открыть базу данных.
4. Запустить контейнер. В терминале IntelliJ IDEA прописать команду "docker-compose up -d"
5. Запустить приложение. Для этого в командной строке указать через флаг -D url к необходимой СУБД:

* для MYSQL java '-Dspring.datasource.url=jdbc:mysql://localhost:3306/app' -jar ./artifacts/aqa-shop.jar

* для PostgreSQL java -jar ./artifacts/aqa-shop.jar -P:jdbc.url=jdbc:postgresql://localhost:5432/app

6. Запустить тесты. Для этого в командной строке указать через флаг -D url к необходимой СУБД:

* для MYSQL  ./gradlew clean test '-Ddb.url=jdbc:mysql://localhost:3306/app'
* для PostgreSQL ./gradlew clean test '-Ddb.url=jdbc:postgresql://localhost:5432/app'

7. Сформировать отчет в терминале командой: ./gradlew allureServe.
8. При необходимости отключения SUT, находясь в терминале Intellij IDEA, нажать клавиши CTRL+C.
9. При необходимости отключения контейнеров ввести в терминале Intellij IDEA команду docker-compose down.