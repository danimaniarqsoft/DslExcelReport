package com.danimaniarqsoft.report.mockfactory;

import java.util.Date;

import org.mockito.Mockito;

import com.danimaniarqsoft.report.model.Persona;

public class MockFactory {

  private MockFactory() {}

  public static Persona getPersona() {
    Persona persona = Mockito.mock(Persona.class);
    Mockito.when(persona.getPersona()).thenReturn("Mozart");
    Mockito.when(persona.getCantidad()).thenReturn(23.2d);
    Mockito.when(persona.getEdad()).thenReturn(130);
    Mockito.when(persona.getFecha()).thenReturn(new Date());
    return persona;
  }
}
