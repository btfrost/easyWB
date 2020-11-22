import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class GUI extends wbProfile implements ActionListener{

    // Global Variables

        FileOutputStream fos;

        {
                try {
                        fos = new FileOutputStream("saved-profiles.txt",true);
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
        }

        PrintWriter writer = new PrintWriter(new OutputStreamWriter(fos));

        private final JFrame frame;

        private final JPanel wbPanel;

        private JLabel typeLabel, tailNumberLabel, weightLabel, armLabel, momentLabel, emptyWeightLabel, pilotFrontPassengerLabel;
        private JLabel rearPassengerLabel, baggageArea1Label, baggageArea2Label, zeroFuelWeightLabel;
        private JLabel fuelInPoundsLabel, rampWeightLabel, taxiRunUpLabel, takeoffWeightLabel, fuelBurnLabel, landingWeightLabel;

        private final JButton remodeButton;
        private final JButton accmodeButton;

        private final JButton loadButton;
        private final JButton saveButton;
        private final JButton calculateButton;

        private JTextField typeTextField;
        private JTextField tailNumberTextField;
        private JTextField emptyWeightWeightTextField;
        private JTextField emptyWeightArmTextField;
        private JTextField emptyWeightMomentTextField;
        private JTextField pilotFrontPassengerWeightTextField;
        private JTextField pilotFrontPassengerArmTextField;
        private JTextField pilotFrontPassengerMomentTextField;
        private JTextField rearPassengerWeightTextField;
        private JTextField rearPassengerArmTextField;
        private JTextField rearPassengerMomentTextField;
        private JTextField baggageArea1WeightTextField;
        private JTextField baggageArea1ArmTextField;
        private JTextField baggageArea1MomentTextField;
        private JTextField baggageArea2WeightTextField;
        private JTextField baggageArea2ArmTextField;
        private JTextField baggageArea2MomentTextField;
        private JTextField zeroFuelWeightWeightTextField;
        private JTextField zeroFuelWeightArmTextField;
        private JTextField zeroFuelWeightMomentTextField;
        private JTextField fuelInPoundsWeightTextField;
        private JTextField fuelInPoundsArmTextField;
        private JTextField fuelInPoundsMomentTextField;
        private JTextField rampWeightWeightTextField;
        private JTextField rampWeightArmTextField;
        private JTextField rampWeightMomentTextField;
        private JTextField taxiRunUpWeightTextField;
        private JTextField taxiRunUpArmTextField;
        private JTextField taxiRunUpMomentTextField;
        private JTextField takeoffWeightWeightTextField;
        private JTextField takeoffWeightArmTextField;
        private JTextField takeoffWeightMomentTextField;
        private JTextField fuelBurnWeightTextField;
        private JTextField fuelBurnArmTextField;
        private JTextField fuelBurnMomentTextField;
        private JTextField landingWeightWeightTextField;
        private JTextField landingWeightArmTextField;
        private JTextField landingWeightMomentTextField;

        //     HashMap that contains saved profiles.
        // (K) Key = Tail Number
        // (V) Mapped Value = wbProfile object that contains empty w&b and type.
        HashMap<String, wbProfile> profileHashMap = new HashMap<>();

    public GUI() {

            // Frame //

            frame = new JFrame("easyWB: For all your weight and balance needs");
            frame.setSize(300, 500);
            frame.setResizable(false);

            // Buttons //

            // REM Button
            remodeButton = new JButton("Rough Estimate Mode");
            remodeButton.addActionListener(this);
            remodeButton.setPreferredSize(new Dimension(50, 30));
            remodeButton.setBounds(50, 50, 50, 30);
            remodeButton.setBackground(Color.orange);
            remodeButton.setFocusable(false);
            remodeButton.setFont(new Font("Roboto", Font.PLAIN, 15));

            // AM Button
            accmodeButton = new JButton("Accurate Mode");
            accmodeButton.addActionListener(this);
            accmodeButton.setPreferredSize(new Dimension(70, 70));
            accmodeButton.setBounds(50, 50, 50, 30);
            accmodeButton.setBackground(Color.orange);
            accmodeButton.setFocusable(false);
            accmodeButton.setFont(new Font("Roboto", Font.PLAIN, 15));

            // Load Empty Weight and Balance Profile Button
            loadButton = new JButton("Load");
            loadButton.addActionListener(this);
            loadButton.setPreferredSize(new Dimension(50, 30));
            loadButton.setBounds(50, 50, 50, 30);
            loadButton.setBackground(Color.orange);
            loadButton.setFocusable(false);
            loadButton.setFont(new Font("Roboto", Font.PLAIN, 15));

            // Save Empty Weight and Balance Profile Button
            saveButton = new JButton("Save");
            saveButton.addActionListener(this);
            saveButton.setPreferredSize(new Dimension(50, 30));
            saveButton.setBounds(50, 50, 50, 30);
            saveButton.setBackground(Color.orange);
            saveButton.setFocusable(false);
            saveButton.setFont(new Font("Roboto", Font.PLAIN, 15));

            // Calculate Button
            calculateButton = new JButton("Calculate");
            calculateButton.addActionListener(this);
            calculateButton.setPreferredSize(new Dimension(50, 30));
            calculateButton.setBounds(50, 50, 50, 30);
            calculateButton.setBackground(Color.orange);
            calculateButton.setFocusable(false);
            calculateButton.setFont(new Font("Roboto", Font.PLAIN, 15));

            // Labels //

            // Welcome Label
            JLabel welcomeLabel = new JLabel("Welcome to easyWB! Please select an option.");
            welcomeLabel.setForeground(Color.orange);
            welcomeLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
            welcomeLabel.setBounds(50, 50, 50, 30);

            wbPanelLabelCreate();

            // Text Fields //

            createTextFields();

            // Panels //

            // Welcome Panel, disappears after initial option select
            JPanel welcomePanel = new JPanel();
            welcomePanel.setBorder(BorderFactory.createEmptyBorder(300, 300, 500, 300));
            welcomePanel.setLayout(new GridLayout(3, 1, 30, 30));
            welcomePanel.add(welcomeLabel);
            welcomePanel.add(remodeButton);
            welcomePanel.add(accmodeButton);
            welcomePanel.setBackground(Color.darkGray);

            // WB Panel
            wbPanel = new JPanel();
            wbPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
            wbPanel.setLayout(new GridLayout(15, 4, 15, 10));
            wbPanelLabelAndTextFieldAdd();
            wbPanel.setBackground(Color.darkGray);


            // Additional Frame Commands //

            frame.setContentPane(welcomePanel);
            frame.invalidate();
            frame.validate();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

            // Read in info from profile file, and store into HashMap //
            try{
                    File file = new File("saved-profiles.txt");
                    Scanner read = new Scanner(file);
                    read.useDelimiter(" ");
                    String readType, readTailNum, readWeight, readArm, readMoment;
                    while (read.hasNextLine()) {
                            readType = read.next();
                            System.out.println(readType);
                            readTailNum = read.next();
                            System.out.println(readTailNum);
                            readWeight = read.next();
                            System.out.println(readWeight);
                            readArm = read.next();
                            System.out.println(readArm);
                            readMoment = read.next();
                            System.out.println(readMoment);
                            if(readType != null && readTailNum != null && readWeight != null &&
                            readArm != null && readMoment != null){
                                    wbProfile wb = new wbProfile(readType, readTailNum, readWeight, readArm, readMoment);
                                    profileHashMap.put(readTailNum, wb);
                            }
                            if(read.hasNextLine() && read.nextLine().equals("")){
                                    break;
                            }
                    }
                    read.close();
            } catch (Exception ex) {
                    ex.printStackTrace();
            }

    }

    // Actions to be taken when each button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getSource()==remodeButton){
                    // Panel switch
                    frame.setContentPane(wbPanel);
                    frame.invalidate();
                    frame.validate();
                    pilotFrontPassengerArmTextField.setText("37");
                    rearPassengerArmTextField.setText("73");
                    baggageArea1ArmTextField.setText("95");
                    baggageArea2ArmTextField.setText("123");
                    fuelInPoundsArmTextField.setText("48");
                    taxiRunUpArmTextField.setText("48");
                    fuelBurnArmTextField.setText("48");
            }
            if (e.getSource()==accmodeButton){
                    frame.setContentPane(wbPanel);
                    frame.invalidate();
                    frame.validate();
            }
            if (e.getSource()==loadButton){
                    String tailNumber = tailNumberTextField.getText();
                    typeTextField.setText(profileHashMap.get(tailNumber).getType());
                    emptyWeightWeightTextField.setText(profileHashMap.get(tailNumber).getEmptyWeight());
                    emptyWeightArmTextField.setText(profileHashMap.get(tailNumber).getEmptyArm());
                    emptyWeightMomentTextField.setText(profileHashMap.get(tailNumber).getEmptyMoment());
            }
            if (e.getSource()==saveButton){
                    // Store values into wbProfile object, then put into hashMap
                    String type;
                    String tailNumber;
                    String emptyWeight;
                    String emptyArm;
                    String emptyMoment;

                    if (typeTextField.getText().isEmpty()){ type = "1"; }
                    else { type = typeTextField.getText(); }

                    if (tailNumberTextField.getText().isEmpty()){ tailNumber = ""; }
                    else { tailNumber = tailNumberTextField.getText(); }

                    if (emptyWeightWeightTextField.getText().isEmpty()){ emptyWeight = ""; }
                    else { emptyWeight = emptyWeightWeightTextField.getText(); }

                    if (emptyWeightArmTextField.getText().isEmpty()){ emptyArm = ""; }
                    else { emptyArm = emptyWeightArmTextField.getText(); }

                    if (emptyWeightMomentTextField.getText().isEmpty()){ emptyMoment = ""; }
                    else { emptyMoment = emptyWeightMomentTextField.getText(); }

                    // Store new info into HashMap
                    wbProfile wb = new wbProfile(type, tailNumber, emptyWeight, emptyArm, emptyMoment);
                    profileHashMap.put(tailNumber, wb);

                    writer.print(type + " ");
                    writer.print(tailNumber + " ");
                    writer.print(emptyWeight + " ");
                    writer.print(emptyArm + " ");
                    writer.print(emptyMoment + " ");
                    writer.print("\n");
                    writer.flush();
            }
            if (e.getSource()==calculateButton){
                calculation();
            }
    }

    public void calculation() {
            double momentCalc ;
            double weightComponent;
            double armComponent;
            String output;
            DecimalFormat dc = new DecimalFormat("0.00");

            // Pilot / Front Passenger Calculation //
            if (pilotFrontPassengerWeightTextField.getText().equals("")){
                    pilotFrontPassengerWeightTextField.setText("0.00");
                    weightComponent = 0;
            } else {
                    weightComponent = Double.parseDouble(pilotFrontPassengerWeightTextField.getText());
            }
            if (pilotFrontPassengerArmTextField.getText().equals("")) {
                    pilotFrontPassengerArmTextField.setText("0.00");
                    armComponent = 0;
            } else {
                    armComponent = Double.parseDouble(pilotFrontPassengerArmTextField.getText());
            }
            momentCalc = weightComponent * armComponent;
            output = dc.format(momentCalc);
            pilotFrontPassengerMomentTextField.setText(output);

            // Rear Passenger Calculation //
            if (rearPassengerWeightTextField.getText().equals("")){
                    rearPassengerWeightTextField.setText("0.00");
                    weightComponent = 0;
            } else {
                    weightComponent = Double.parseDouble(rearPassengerWeightTextField.getText());
            }
            if (rearPassengerArmTextField.getText().equals("")) {
                    rearPassengerArmTextField.setText("0.00");
                    armComponent = 0;
            } else {
                    armComponent = Double.parseDouble(rearPassengerArmTextField.getText());
            }
            momentCalc = weightComponent * armComponent;
            output = dc.format(momentCalc);
            rearPassengerMomentTextField.setText(output);

            // Baggage Area 1 Calculation //
            if (baggageArea1WeightTextField.getText().equals("")){
                    baggageArea1WeightTextField.setText("0.00");
                    weightComponent = 0;
            } else {
                    weightComponent = Double.parseDouble(baggageArea1WeightTextField.getText());
            }
            if (baggageArea1ArmTextField.getText().equals("")) {
                    baggageArea1ArmTextField.setText("0.00");
                    armComponent = 0;
            } else {
                    armComponent = Double.parseDouble(baggageArea1ArmTextField.getText());
            }
            momentCalc = weightComponent * armComponent;
            output = dc.format(momentCalc);
            baggageArea1MomentTextField.setText(output);

            // Baggage Area 2 Calculation //
            if (baggageArea2WeightTextField.getText().equals("")){
                    baggageArea2WeightTextField.setText("0.00");
                    weightComponent = 0;
            } else {
                    weightComponent = Double.parseDouble(baggageArea2WeightTextField.getText());
            }
            if (baggageArea2ArmTextField.getText().equals("")) {
                    baggageArea2ArmTextField.setText("0.00");
                    armComponent = 0;
            } else {
                    armComponent = Double.parseDouble(baggageArea2ArmTextField.getText());
            }
            momentCalc = weightComponent * armComponent;
            output = dc.format(momentCalc);
            baggageArea2MomentTextField.setText(output);

            // Zero Fuel Weight Calculation //
            weightComponent = (Double.parseDouble(emptyWeightWeightTextField.getText()) +
                    Double.parseDouble(pilotFrontPassengerWeightTextField.getText()) +
                    Double.parseDouble(rearPassengerWeightTextField.getText()) +
                    Double.parseDouble(baggageArea1WeightTextField.getText()) +
                    Double.parseDouble(baggageArea2WeightTextField.getText()));
            output = dc.format(weightComponent);
            zeroFuelWeightWeightTextField.setText(output);
            momentCalc = (Double.parseDouble(emptyWeightMomentTextField.getText()) +
                    Double.parseDouble(pilotFrontPassengerMomentTextField.getText()) +
                    Double.parseDouble(rearPassengerMomentTextField.getText()) +
                    Double.parseDouble(baggageArea1MomentTextField.getText()) +
                    Double.parseDouble(baggageArea2MomentTextField.getText()));
            output = dc.format(momentCalc);
            zeroFuelWeightMomentTextField.setText(output);
            armComponent = momentCalc / weightComponent;
            output = dc.format(armComponent);
            zeroFuelWeightArmTextField.setText(output);

            // Fuel in Pound Calculation //
            if (fuelInPoundsWeightTextField.getText().equals("")){
                    fuelInPoundsWeightTextField.setText("0.00");
                    weightComponent = 0;
            } else {
                    weightComponent = Double.parseDouble(fuelInPoundsWeightTextField.getText());
            }
            if (fuelInPoundsArmTextField.getText().equals("")) {
                    fuelInPoundsArmTextField.setText("0.00");
                    armComponent = 0;
            } else {
                    armComponent = Double.parseDouble(fuelInPoundsArmTextField.getText());
            }
            momentCalc = weightComponent * armComponent;
            output = dc.format(momentCalc);
            fuelInPoundsMomentTextField.setText(output);

            // Ramp Weight Calculation //
            weightComponent = (Double.parseDouble(zeroFuelWeightWeightTextField.getText()) +
                    Double.parseDouble(fuelInPoundsWeightTextField.getText()));
            output = dc.format(weightComponent);
            rampWeightWeightTextField.setText(output);
            momentCalc = (Double.parseDouble(zeroFuelWeightMomentTextField.getText()) +
                    Double.parseDouble(fuelInPoundsMomentTextField.getText()));
            output = dc.format(momentCalc);
            rampWeightMomentTextField.setText(output);
            armComponent = momentCalc / weightComponent;
            output = dc.format(armComponent);
            rampWeightArmTextField.setText(output);

            // Taxi/Run-Up Calculation //
            if (taxiRunUpWeightTextField.getText().equals("")){
                    taxiRunUpWeightTextField.setText("0.00");
                    weightComponent = 0;
            } else {
                    weightComponent = Double.parseDouble(taxiRunUpWeightTextField.getText());
            }
            if (taxiRunUpArmTextField.getText().equals("")) {
                    taxiRunUpArmTextField.setText("0.00");
                    armComponent = 0;
            } else {
                    armComponent = Double.parseDouble(taxiRunUpArmTextField.getText());
            }
            momentCalc = weightComponent * armComponent;
            output = dc.format(momentCalc);
            taxiRunUpMomentTextField.setText(output);

            // Takeoff Weight Calculation //
            weightComponent = (Double.parseDouble(rampWeightWeightTextField.getText()) +
                    Double.parseDouble(taxiRunUpWeightTextField.getText()));
            output = dc.format(weightComponent);
            takeoffWeightWeightTextField.setText(output);
            momentCalc = (Double.parseDouble(rampWeightMomentTextField.getText()) +
                    Double.parseDouble(taxiRunUpMomentTextField.getText()));
            output = dc.format(momentCalc);
            takeoffWeightMomentTextField.setText(output);
            armComponent = momentCalc / weightComponent;
            output = dc.format(armComponent);
            takeoffWeightArmTextField.setText(output);

            // Fuel Burn Calculation //
            if (fuelBurnWeightTextField.getText().equals("")){
                    fuelBurnWeightTextField.setText("0.00");
                    weightComponent = 0;
            } else {
                    weightComponent = Double.parseDouble(fuelBurnWeightTextField.getText());
            }
            if (fuelBurnArmTextField.getText().equals("")) {
                    fuelBurnArmTextField.setText("0.00");
                    armComponent = 0;
            } else {
                    armComponent = Double.parseDouble(fuelBurnArmTextField.getText());
            }
            momentCalc = weightComponent * armComponent;
            output = dc.format(momentCalc);
            fuelBurnMomentTextField.setText(output);

            // Landing Weight Calculation //
            weightComponent = (Double.parseDouble(takeoffWeightWeightTextField.getText()) +
                    Double.parseDouble(fuelBurnWeightTextField.getText()));
            output = dc.format(weightComponent);
            landingWeightWeightTextField.setText(output);
            momentCalc = (Double.parseDouble(takeoffWeightMomentTextField.getText()) +
                    Double.parseDouble(fuelBurnMomentTextField.getText()));
            output = dc.format(momentCalc);
            landingWeightMomentTextField.setText(output);
            armComponent = momentCalc / weightComponent;
            output = dc.format(armComponent);
            landingWeightArmTextField.setText(output);
    }

    public void createTextFields() {

            // Type and Tail Number Text Fields
            typeTextField = new JTextField(9);
            typeTextField.setHorizontalAlignment(JTextField.CENTER);
            tailNumberTextField = new JTextField(9);
            tailNumberTextField.setHorizontalAlignment(JTextField.CENTER);

            // Empty weight Text Fields
            emptyWeightWeightTextField = new JTextField(9);
            emptyWeightWeightTextField.setHorizontalAlignment(JTextField.CENTER);
            emptyWeightArmTextField = new JTextField(9);
            emptyWeightArmTextField.setHorizontalAlignment(JTextField.CENTER);
            emptyWeightMomentTextField = new JTextField(9);
            emptyWeightMomentTextField.setHorizontalAlignment(JTextField.CENTER);
            // Pilot / Front passenger Text Fields
            pilotFrontPassengerWeightTextField = new JTextField(9);
            pilotFrontPassengerWeightTextField.setHorizontalAlignment(JTextField.CENTER);
            pilotFrontPassengerArmTextField = new JTextField(9);
            pilotFrontPassengerArmTextField.setHorizontalAlignment(JTextField.CENTER);
            pilotFrontPassengerMomentTextField = new JTextField(9);
            pilotFrontPassengerMomentTextField.setHorizontalAlignment(JTextField.CENTER);
            // Rear Passenger Text Fields
            rearPassengerWeightTextField = new JTextField(9);
            rearPassengerWeightTextField.setHorizontalAlignment(JTextField.CENTER);
            rearPassengerArmTextField = new JTextField(9);
            rearPassengerArmTextField.setHorizontalAlignment(JTextField.CENTER);
            rearPassengerMomentTextField = new JTextField(9);
            rearPassengerMomentTextField.setHorizontalAlignment(JTextField.CENTER);
            // Baggage Area 1 Text Fields
            baggageArea1WeightTextField = new JTextField(9);
            baggageArea1WeightTextField.setHorizontalAlignment(JTextField.CENTER);
            baggageArea1ArmTextField = new JTextField(9);
            baggageArea1ArmTextField.setHorizontalAlignment(JTextField.CENTER);
            baggageArea1MomentTextField = new JTextField(9);
            baggageArea1MomentTextField.setHorizontalAlignment(JTextField.CENTER);
            // Baggage Area 2 Text Fields
            baggageArea2WeightTextField = new JTextField(9);
            baggageArea2WeightTextField.setHorizontalAlignment(JTextField.CENTER);
            baggageArea2ArmTextField = new JTextField(9);
            baggageArea2ArmTextField.setHorizontalAlignment(JTextField.CENTER);
            baggageArea2MomentTextField = new JTextField(9);
            baggageArea2MomentTextField.setHorizontalAlignment(JTextField.CENTER);
            // Zero Fuel Weight Text Fields
            zeroFuelWeightWeightTextField = new JTextField(9);
            zeroFuelWeightWeightTextField.setHorizontalAlignment(JTextField.CENTER);
            zeroFuelWeightArmTextField = new JTextField(9);
            zeroFuelWeightArmTextField.setHorizontalAlignment(JTextField.CENTER);
            zeroFuelWeightMomentTextField = new JTextField(9);
            zeroFuelWeightMomentTextField.setHorizontalAlignment(JTextField.CENTER);
            // Fuel in pounds Text Fields
            fuelInPoundsWeightTextField = new JTextField(9);
            fuelInPoundsWeightTextField.setHorizontalAlignment(JTextField.CENTER);
            fuelInPoundsArmTextField = new JTextField(9);
            fuelInPoundsArmTextField.setHorizontalAlignment(JTextField.CENTER);
            fuelInPoundsMomentTextField = new JTextField(9);
            fuelInPoundsMomentTextField .setHorizontalAlignment(JTextField.CENTER);
            // Ramp Weight Text Fields
            rampWeightWeightTextField = new JTextField(9);
            rampWeightWeightTextField.setHorizontalAlignment(JTextField.CENTER);
            rampWeightArmTextField = new JTextField(9);
            rampWeightArmTextField .setHorizontalAlignment(JTextField.CENTER);
            rampWeightMomentTextField = new JTextField(9);
            rampWeightMomentTextField.setHorizontalAlignment(JTextField.CENTER);
            // Taxi Run Up Text Fields
            taxiRunUpWeightTextField = new JTextField(9);
            taxiRunUpWeightTextField .setHorizontalAlignment(JTextField.CENTER);
            taxiRunUpArmTextField = new JTextField(9);
            taxiRunUpArmTextField.setHorizontalAlignment(JTextField.CENTER);
            taxiRunUpMomentTextField = new JTextField(9);
            taxiRunUpMomentTextField.setHorizontalAlignment(JTextField.CENTER);
            // Takeoff Weight Text Fields
            takeoffWeightWeightTextField = new JTextField(9);
            takeoffWeightWeightTextField.setHorizontalAlignment(JTextField.CENTER);
            takeoffWeightArmTextField = new JTextField(9);
            takeoffWeightArmTextField.setHorizontalAlignment(JTextField.CENTER);
            takeoffWeightMomentTextField = new JTextField(9);
            takeoffWeightMomentTextField.setHorizontalAlignment(JTextField.CENTER);
            // Fuel Burn Text Fields
            fuelBurnWeightTextField = new JTextField(9);
            fuelBurnWeightTextField.setHorizontalAlignment(JTextField.CENTER);
            fuelBurnArmTextField = new JTextField(9);
            fuelBurnArmTextField.setHorizontalAlignment(JTextField.CENTER);
            fuelBurnMomentTextField = new JTextField(9);
            fuelBurnMomentTextField.setHorizontalAlignment(JTextField.CENTER);
            // Landing Weight Text Fields
            landingWeightWeightTextField = new JTextField(9);
            landingWeightWeightTextField.setHorizontalAlignment(JTextField.CENTER);
            landingWeightArmTextField = new JTextField(9);
            landingWeightArmTextField.setHorizontalAlignment(JTextField.CENTER);
            landingWeightMomentTextField = new JTextField(9);
            landingWeightMomentTextField.setHorizontalAlignment(JTextField.CENTER);

    }

    public void wbPanelLabelCreate(){

            // Type Label
            typeLabel = new JLabel("Type (S or R)");
            typeLabel.setBounds(50, 50, 50, 30);
            typeLabel.setForeground(Color.orange);
            typeLabel.setHorizontalAlignment(JLabel.CENTER);
            typeLabel.setFont(new Font("Roboto", Font.PLAIN, 13));

            // Tail Number Label
            tailNumberLabel = new JLabel("Tail Number");
            tailNumberLabel.setBounds(50, 50, 50, 30);
            tailNumberLabel.setForeground(Color.orange);
            tailNumberLabel.setHorizontalAlignment(JLabel.CENTER);
            tailNumberLabel.setFont(new Font("Roboto", Font.PLAIN, 13));

            // Weight Label
            weightLabel = new JLabel("WEIGHT");
            weightLabel.setBounds(50, 50, 50, 30);
            weightLabel.setForeground(Color.orange);
            weightLabel.setHorizontalAlignment(JLabel.CENTER);
            weightLabel.setFont(new Font("Roboto", Font.PLAIN, 13));

            // Arm Label
            armLabel = new JLabel("ARM");
            armLabel.setBounds(50, 50, 50, 30);
            armLabel.setForeground(Color.orange);
            armLabel.setHorizontalAlignment(JLabel.CENTER);
            armLabel.setFont(new Font("Roboto", Font.PLAIN, 13));

            // Moment Label
            momentLabel = new JLabel("MOMENT");
            momentLabel.setBounds(50, 50, 50, 30);
            momentLabel.setForeground(Color.orange);
            momentLabel.setHorizontalAlignment(JLabel.CENTER);
            momentLabel.setFont(new Font("Roboto", Font.PLAIN, 13));

            // Empty Weight Label
            emptyWeightLabel = new JLabel("Empty Weight");
            emptyWeightLabel.setBounds(50, 50, 50, 30);
            emptyWeightLabel.setForeground(Color.orange);

            // Pilot / Front passenger Label
            pilotFrontPassengerLabel = new JLabel("Pilot / Front Passenger");
            pilotFrontPassengerLabel.setBounds(50, 50, 50, 30);
            pilotFrontPassengerLabel.setForeground(Color.orange);
            pilotFrontPassengerLabel.setFont(new Font("Roboto", Font.PLAIN, 13));

            // Rear passenger Label
            rearPassengerLabel = new JLabel("Rear Passenger");
            rearPassengerLabel.setBounds(50, 50, 50, 30);
            rearPassengerLabel.setForeground(Color.orange);
            rearPassengerLabel.setFont(new Font("Roboto", Font.PLAIN, 13));

            // Baggage area 1 Label
            baggageArea1Label = new JLabel("Baggage Area 1");
            baggageArea1Label.setBounds(50, 50, 50, 30);
            baggageArea1Label.setForeground(Color.orange);
            baggageArea1Label.setFont(new Font("Roboto", Font.PLAIN, 13));

            // Baggage area 2 Label
            baggageArea2Label = new JLabel("Baggage Area 2");
            baggageArea2Label.setBounds(50, 50, 50, 30);
            baggageArea2Label.setForeground(Color.orange);
            baggageArea2Label.setFont(new Font("Roboto", Font.PLAIN, 13));

            // Zero Fuel Weight Label
            zeroFuelWeightLabel = new JLabel("Zero Fuel Weight");
            zeroFuelWeightLabel.setBounds(50, 50, 50, 30);
            zeroFuelWeightLabel.setForeground(Color.orange);
            zeroFuelWeightLabel.setFont(new Font("Roboto", Font.PLAIN, 13));

            // Fuel in pounds Label
            fuelInPoundsLabel = new JLabel("Fuel in Pounds");
            fuelInPoundsLabel.setBounds(50, 50, 50, 30);
            fuelInPoundsLabel.setForeground(Color.orange);
            fuelInPoundsLabel.setFont(new Font("Roboto", Font.PLAIN, 13));

            // Ramp weight Label
            rampWeightLabel = new JLabel("Ramp Weight");
            rampWeightLabel.setBounds(50, 50, 50, 30);
            rampWeightLabel.setForeground(Color.orange);
            rampWeightLabel.setFont(new Font("Roboto", Font.PLAIN, 13));

            // Taxi / Run up Label
            taxiRunUpLabel = new JLabel("Taxi/Run-Up");
            taxiRunUpLabel.setBounds(50, 50, 50, 30);
            taxiRunUpLabel.setForeground(Color.orange);
            taxiRunUpLabel.setFont(new Font("Roboto", Font.PLAIN, 13));

            // Takeoff weight Label
            takeoffWeightLabel = new JLabel("Takeoff Weight");
            takeoffWeightLabel.setBounds(50, 50, 50, 30);
            takeoffWeightLabel.setForeground(Color.orange);
            takeoffWeightLabel.setFont(new Font("Roboto", Font.PLAIN, 13));

            // Fuel burn Label
            fuelBurnLabel = new JLabel("Fuel Burn");
            fuelBurnLabel.setBounds(50, 50, 50, 30);
            fuelBurnLabel.setForeground(Color.orange);
            fuelBurnLabel.setFont(new Font("Roboto", Font.PLAIN, 13));

            // Landing weight Label
            landingWeightLabel = new JLabel("Landing Weight");
            landingWeightLabel.setBounds(50, 50, 50, 30);
            landingWeightLabel.setForeground(Color.orange);
            landingWeightLabel.setFont(new Font("Roboto", Font.PLAIN, 13));

    }
    public void wbPanelLabelAndTextFieldAdd() {

            wbPanel.add(calculateButton);
            wbPanel.add(new JLabel());
            wbPanel.add(typeLabel);
            wbPanel.add(tailNumberLabel);

            wbPanel.add(saveButton);
            wbPanel.add(loadButton);
            wbPanel.add(typeTextField);
            wbPanel.add(tailNumberTextField);

            wbPanel.add(new JLabel());
            wbPanel.add(weightLabel);
            wbPanel.add(armLabel);
            wbPanel.add(momentLabel);

            wbPanel.add(emptyWeightLabel);
            wbPanel.add(emptyWeightWeightTextField);
            wbPanel.add(emptyWeightArmTextField);
            wbPanel.add(emptyWeightMomentTextField);

            wbPanel.add(pilotFrontPassengerLabel);
            wbPanel.add(pilotFrontPassengerWeightTextField);
            wbPanel.add(pilotFrontPassengerArmTextField);
            wbPanel.add(pilotFrontPassengerMomentTextField);

            wbPanel.add(rearPassengerLabel);
            wbPanel.add(rearPassengerWeightTextField);
            wbPanel.add(rearPassengerArmTextField);
            wbPanel.add(rearPassengerMomentTextField);

            wbPanel.add(baggageArea1Label);
            wbPanel.add(baggageArea1WeightTextField);
            wbPanel.add(baggageArea1ArmTextField);
            wbPanel.add(baggageArea1MomentTextField);

            wbPanel.add(baggageArea2Label);
            wbPanel.add(baggageArea2WeightTextField);
            wbPanel.add(baggageArea2ArmTextField);
            wbPanel.add(baggageArea2MomentTextField);

            wbPanel.add(zeroFuelWeightLabel);
            wbPanel.add(zeroFuelWeightWeightTextField);
            wbPanel.add(zeroFuelWeightArmTextField);
            wbPanel.add(zeroFuelWeightMomentTextField);

            wbPanel.add(fuelInPoundsLabel);
            wbPanel.add(fuelInPoundsWeightTextField);
            wbPanel.add(fuelInPoundsArmTextField);
            wbPanel.add(fuelInPoundsMomentTextField);

            wbPanel.add(rampWeightLabel);
            wbPanel.add(rampWeightWeightTextField);
            wbPanel.add(rampWeightArmTextField);
            wbPanel.add(rampWeightMomentTextField);

            wbPanel.add(taxiRunUpLabel);
            wbPanel.add(taxiRunUpWeightTextField);
            wbPanel.add(taxiRunUpArmTextField);
            wbPanel.add(taxiRunUpMomentTextField);

            wbPanel.add(takeoffWeightLabel);
            wbPanel.add(takeoffWeightWeightTextField);
            wbPanel.add(takeoffWeightArmTextField);
            wbPanel.add(takeoffWeightMomentTextField);

            wbPanel.add(fuelBurnLabel);
            wbPanel.add(fuelBurnWeightTextField);
            wbPanel.add(fuelBurnArmTextField);
            wbPanel.add(fuelBurnMomentTextField);

            wbPanel.add(landingWeightLabel);
            wbPanel.add(landingWeightWeightTextField);
            wbPanel.add(landingWeightArmTextField);
            wbPanel.add(landingWeightMomentTextField);

    }

}
