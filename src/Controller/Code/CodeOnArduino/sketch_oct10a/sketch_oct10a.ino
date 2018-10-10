void setup() {
  // put your setup code here, to run once:
  DDRB |= 0x01;
  PCICR  |= 0b00000001;    // turn on port b
  PCMSK0 |= 0b00000110;
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:

}

ISR(PCINT0_vect)
{
    static uint8_t prev = 0x00;
    uint8_t current, changed;

      
    current = PINB; // get input state of portC as it has now changed
    changed = current ^ prev; // use XOR to find out which bit(s) have changed
    if (changed & (1 << PB1)) {
          Serial.write("Send enable code");
       }
    if (changed & (1 << PB2)) {
         // handle change on PC5
         Serial.write("send shot code");

    }
    prev = current; // remember for next time


}
