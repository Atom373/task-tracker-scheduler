 <h1>Task tracker scheduler service</h1>
<p>
  Проект был реализован в соответствии с 
  <a href="https://zhukovsd.github.io/java-backend-learning-course/Projects/TaskTracker/">
     тех. заданием.
  </a>
</p>
<p>
   Сервис, который занимается сканированием всех данных в базе, формированием отчетов о том, сколько
   задача пользователь выполнил за сегодня и сколько ему еще нужно выполнить (вкючая заголовки этих задач).
   Каждый подобный отчет упаковывается в объект EmailMessage и отправляется в очередь сообщений RabbitMQ.
</p>
<h3>Использованные технологии:</h3>
<p>
  Postgresql, RabbitMQ Lombok, JUnit, Spring Boot, Spring Data Jpa, Spring AMQP, Spring Scheduler.
</p>
