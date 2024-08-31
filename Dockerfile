# Temel RabbitMQ imajını kullan
FROM rabbitmq:3-management

# Özel konfigürasyon dosyasını ekleyin
COPY rabbitmq.conf /etc/rabbitmq/rabbitmq.conf

CMD ["rabbitmq-server"]
