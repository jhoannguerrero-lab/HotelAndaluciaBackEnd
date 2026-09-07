
INSERT IGNORE INTO habitaciones (id, numero, tipo, piso, capacidad, precio_noche, estado, created_at) VALUES
(1, '101', 'INDIVIDUAL', 1, 1, 220.00, 'OCUPADA', NOW()),
(2, '102', 'INDIVIDUAL', 1, 1, 220.00, 'DISPONIBLE', NOW()),
(3, '110', 'DOBLE', 1, 2, 340.00, 'DISPONIBLE', NOW()),
(4, '203', 'DOBLE', 2, 2, 480.00, 'OCUPADA', NOW()),
(5, '205', 'DOBLE', 2, 2, 480.00, 'DISPONIBLE', NOW()),
(6, '305', 'SUITE_FAMILIAR', 3, 4, 720.00, 'OCUPADA', NOW()),
(7, '307', 'SUITE_FAMILIAR', 3, 4, 720.00, 'MANTENIMIENTO', NOW()),
(8, '108', 'INDIVIDUAL', 1, 1, 220.00, 'DISPONIBLE', NOW());

INSERT IGNORE INTO reservas (id, habitacion_id, usuario_id, huesped_nombre, huesped_documento, huesped_telefono, checkin, checkout, num_huespedes, metodo_pago, estado, notas, created_at) VALUES
(1, 4, 2, 'Maria Fernanda Rojas', '8452136 LP', '71234567', '2026-09-05', '2026-09-08', 2, 'TARJETA', 'CONFIRMADA', NULL, NOW()),
(2, 1, 2, 'Jorge Antonio Salazar', '6231458 CB', '76543210', '2026-09-05', '2026-09-06', 1, 'EFECTIVO', 'PENDIENTE', NULL, NOW()),
(3, 6, 1, 'Familia Quispe Mamani', '5127643 SC', '70011223', '2026-09-06', '2026-09-10', 4, 'TRANSFERENCIA', 'CONFIRMADA', 'Solicitan cuna adicional', NOW()),
(4, 3, 2, 'Ana Lucia Fernandez', '4478215 LP', '75566778', '2026-09-02', '2026-09-04', 2, 'TARJETA', 'FINALIZADA', NULL, NOW()),
(5, 5, 1, 'Ricardo Pena Coca', '3392841 CB', '78899001', '2026-09-01', '2026-09-03', 2, 'TARJETA', 'CANCELADA', NULL, NOW());
