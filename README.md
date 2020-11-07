# easyWB
Helps you calculate weight and balance for a Cessna 172 (R or S model), as well as other useful calculations.

This should be used to double-check a hand-written weight and balance.

Two modes:

Rough Estimate Mode
- Will not be completely accurate as the Arm values are based off of the 172-R/172-S POH. Faster but not truly accurate and therefore not as safe to use.

Accurate Mode
- Allows for insertion of custom Arm values for accurate calculations. Takes more time but is more accurate and therefore safer.

Values required for complete run:
- (select) R or S model
- Empty weight (Weight, Arm, Movement)
- Pilot / Front passenger combined weight, Arm
- Rear passenger weight, Arm
- Baggage area 1 weight, Arm
- Baggage area 2 weight, Arm
- Fuel in pounds, Arm

Features to implement:
- Ability to save empty weight and balance associated with a tail number
- Calculate missing parts of Weight and Balance
- Display full weight and balance
- Display CG Takeoff location in inches aft of datum
- Display CG Landing Location in inches aft of datum
- Calculate and display pressure altitude, given current pressure and field elevation
- Calculate and display density altitude, given current temperature (C or F)

Once these are done, possible advanced features such as:
- Calculate and display takeoff distance (ground roll)
- Calculate and display takeoff distance (over 50 ft. Obstacle / short field)
- Calculate and display landing distance (ground roll)
- Calculate and display %BHP, Power Setting, KTAS, Fuel Consumption (All used for Straight-And-Level Flight/Cruise calculation)
- Calculate and display Range, Endurance
- Calculate and display crosswind component
