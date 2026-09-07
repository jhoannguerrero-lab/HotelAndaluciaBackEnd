package com.hotelandalucia.backend.model;
/**
 * Rol del usuario dentro del sistema.
 * CONSULTA: solo puede ver (GET) habitaciones y reservas.
 * GESTOR: puede ver y ademas registrar/editar/eliminar (POST, PUT, DELETE).
 */
public enum Rol {
    CONSULTA,
    GESTOR
}