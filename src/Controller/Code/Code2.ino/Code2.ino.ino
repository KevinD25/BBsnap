int lastpress1 = 0;
int lastpress2 = 0;
int debounce = 15;
boolean isEnabled = false;
boolean takeShot = false;




void setup()
{
  adc_disable()          // adc disable for low power consumption

  DDRB =  0b00001000;    // pin 5 as output for the infrared
  GIMSK = 0b00100000;    // turns on pin change interrupts
  PCMSK = 0b00000110;    // turn on interrupts on pins 6 and 7
  sei();
  void loop() {
    // put your main code here, to run repeatedly:


  }

  //interrupt on enable button
  ISR(PCINT1_vect)
  {
    if (lastpress1 < millis())
    {
      lastpress1 = millis() + debounce

                   if (isEnabled = false)
      {
        isEnabled = true
      }

      else
      {
        isEnabled = true
      }

    }
  }
}

//interrupt on shot button
ISR(PCINT2_vect)
{
  if (lastpress2 < millis())
  {
    lastpress2 = millis() + debounce

                 if (takeShot = false)
    {
      takeShot = true
    }

    else
    {
      takeShot = true
    }

  }
}
}

