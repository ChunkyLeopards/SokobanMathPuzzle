import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;


public class MathPanel {
   
   private JPanel problemPanel;
   private JPanel inputPanel;
   private JPanel functionPanel;
   private JPanel buttonPanel;
   private JPanel groupButtonPanel;
   private JPanel symbolButtonPanel;
   private JPanel symbolButtonPanelOptions[];
   private JPanel inputLayer;
   private JTextArea description;
   private String groupButtonNames[][];
   private String symbolButtonNames[][][];
   private JButton groupButtons[][];
   private JButton symbolButtons[][][];
   private String groupLaTeX[][];
   private String symbolLaTeX[][][];
   private TeXFormula groupForm[][];
   private TeXFormula symbolForm[][][];
   private TeXIcon groupImage[][];
   private TeXIcon symbolImage[][][];
   private JLabel function;
   private String formulaLaTeX;
   private TeXFormula formula;
   private TeXIcon functionImage;
   private Input input;
   private CardLayout symbolOptions;
   private MathTemplateInterpreter puzzle;
   private JButton submit;
   private JButton giveUp;
   
   public JPanel createMath() {
      
      puzzle = new MathTemplateInterpreter();
      problemPanel = new JPanel();
      problemPanel.setLayout(new BoxLayout(problemPanel, BoxLayout.PAGE_AXIS));
      formulaLaTeX = puzzle.getProblem();
      formula = new TeXFormula(formulaLaTeX);
      functionImage = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY)
                             .setSize(30)
                             .setFGColor(Color.white)
                             .setWidth(TeXConstants.UNIT_PIXEL, 256f, TeXConstants.ALIGN_CENTER)
                             .setIsMaxWidth(true).setInterLineSpacing(TeXConstants.UNIT_PIXEL, 20f)
                             .build();
      functionPanel = new JPanel();
      functionPanel.setBackground(Color.black);
      function = new JLabel(functionImage);
      function.setMaximumSize(new Dimension(100,300));
      function.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
      functionPanel.add(function);
      problemPanel.add(functionPanel);
      return problemPanel;
        
   }
   
   public JPanel createInput() {

      symbolOptions = new CardLayout();
      inputPanel = new JPanel();
      inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
      description = new JTextArea(puzzle.getDescription());
      description.setBackground(Color.black);
      description.setLineWrap(true);
      description.setWrapStyleWord(true);
      description.setFont(new Font("TimesRoman", Font.PLAIN, 14));
      description.setForeground(Color.white);
      description.setBorder(new EmptyBorder(5, 5, 5, 5));
      description.setEditable(false);
      description.setHighlighter(null);
      input = new Input();
      submit = new JButton("Submit");
      giveUp = new JButton("Give Up?");
      inputLayer = new JPanel();
      inputLayer.setLayout(new BoxLayout(inputLayer, BoxLayout.LINE_AXIS));
      buttonPanel = new JPanel();
      buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
      buttonPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
      groupButtonPanel = new JPanel();
      buttonPanel.add(groupButtonPanel);
      buttonPanel.add(Box.createRigidArea(new Dimension(15,0)));
      groupButtonPanel.setLayout(new GridLayout(4, 4));
      symbolButtonPanel = new JPanel();
      buttonPanel.add(symbolButtonPanel);
      symbolButtonPanel.setLayout(symbolOptions);
      groupLaTeX = new String[4][4];
      symbolLaTeX = new String[14][5][5];
      groupForm = new TeXFormula[4][4];
      symbolForm = new TeXFormula[14][5][5];
      groupImage = new TeXIcon[4][4];
      symbolImage = new TeXIcon[14][5][5];
      groupButtons = new JButton[4][4];
      symbolButtons = new JButton[14][5][5];
      groupButtonNames = new String[4][4];
      symbolButtonNames = new String[14][5][5];
      symbolButtonPanelOptions = new JPanel[14];

      groupButtonNames[0][0] = "Lowercase Greek Letters";
      groupButtonNames[0][1] = "Uppercase Greek Letters";
      groupButtonNames[0][2] = "Functions";
      groupButtonNames[0][3] = "Set Theory Symbols";
      groupButtonNames[1][0] = "Relational Operators";
      groupButtonNames[1][1] = "Logic Symbols";
      groupButtonNames[1][2] = "Trigonometric Symbols";
      groupButtonNames[1][3] = "Geometric Symbols";
      groupButtonNames[2][0] = "Number Sets";
      groupButtonNames[2][1] = "Operations";
      groupButtonNames[2][2] = "Constants";
      groupButtonNames[2][3] = "Statistics";
      groupButtonNames[3][0] = "Combinatorics";
      groupButtonNames[3][1] = "Accents";
      
      groupLaTeX[0][0] = "\\alpha"; //lowercase greek letters
      groupLaTeX[0][1] = "\\Omega"; //uppercase greek letters
      groupLaTeX[0][2] = "\\int"; //functions
      groupLaTeX[0][3] = "\\cap"; //sets
      groupLaTeX[1][0] = "\\neq"; //relation
      groupLaTeX[1][1] = "\\wedge"; //logic
      groupLaTeX[1][2] = "\\sin"; //trigonometry
      groupLaTeX[1][3] = "\\parallel"; //geometry
      groupLaTeX[2][0] = "\\mathbb{R}"; //number sets
      groupLaTeX[2][1] = "\\pm"; //operations
      groupLaTeX[2][2] = "\\pi"; //constants
      groupLaTeX[2][3] = "\\%"; //statistics
      groupLaTeX[3][0] = "!"; //combinatorics
      groupLaTeX[3][1] = "\\hat"; //accents
      
      for (int i = 0; i < groupLaTeX.length; i++) {
         
         for (int j = 0; j < groupLaTeX[0].length; j++) {
            
            if (groupLaTeX[i][j] != null) {

               groupForm[i][j] = new TeXFormula(groupLaTeX[i][j]);
               groupImage[i][j] = groupForm[i][j].new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(15)
                     .setWidth(TeXConstants.UNIT_PIXEL, 256f, TeXConstants.ALIGN_CENTER).setIsMaxWidth(true)
                     .setInterLineSpacing(TeXConstants.UNIT_PIXEL, 20f).build();
               groupButtons[i][j] = new JButton();
               groupButtons[i][j].setIcon(groupImage[i][j]);
               groupButtons[i][j].setToolTipText(groupButtonNames[i][j]);
               groupButtons[i][j].setActionCommand(groupButtonNames[i][j]);
               groupButtons[i][j].setFocusPainted(false);
               
               groupButtons[i][j].addActionListener(new ActionListener() {

                  @Override
                  public void actionPerformed(ActionEvent arg0) {

                     symbolOptions.show(symbolButtonPanel, arg0.getActionCommand());

                  }

               });

               groupButtonPanel.add(groupButtons[i][j]);
            
            }
            
         }
            
      }
      
      symbolButtonNames[0][0][0] = "Lowercase Alpha"; //lowercase greek letters
      symbolButtonNames[0][0][1] = "Lowercase Beta";
      symbolButtonNames[0][0][2] = "Lowercase Gamma";
      symbolButtonNames[0][0][3] = "Lowercase Delta";
      symbolButtonNames[0][0][4] = "Lowercase Epsilon";
      symbolButtonNames[0][1][0] = "Lowercase Zeta";
      symbolButtonNames[0][1][1] = "Lowercase Eta";
      symbolButtonNames[0][1][2] = "Lowercase Theta";
      symbolButtonNames[0][1][3] = "Lowercase Iota";
      symbolButtonNames[0][1][4] = "Lowercase Kappa";
      symbolButtonNames[0][2][0] = "Lowercase Lambda";
      symbolButtonNames[0][2][1] = "Lowercase Mu";
      symbolButtonNames[0][2][2] = "Lowercase Nu";
      symbolButtonNames[0][2][3] = "Lowercase Xi";
      symbolButtonNames[0][2][4] = "Lowercase Omicron";
      symbolButtonNames[0][3][0] = "Lowercase Pi";
      symbolButtonNames[0][3][1] = "Lowercase Rho";
      symbolButtonNames[0][3][2] = "Lowercase Sigma";
      symbolButtonNames[0][3][3] = "Lowercase Tau";
      symbolButtonNames[0][3][4] = "Lowercase Upsilon";
      symbolButtonNames[0][4][0] = "Lowercase Phi";
      symbolButtonNames[0][4][1] = "Lowercase Chi";
      symbolButtonNames[0][4][2] = "Lowercase Psi";
      symbolButtonNames[0][4][3] = "Lowercase Omega";
      symbolButtonNames[1][0][0] = "Uppercase Alpha"; //uppercase greek letters
      symbolButtonNames[1][0][1] = "Uppercase Beta";
      symbolButtonNames[1][0][2] = "Uppercase Gamma";
      symbolButtonNames[1][0][3] = "Uppercase Delta";
      symbolButtonNames[1][0][4] = "Uppercase Epsilon";
      symbolButtonNames[1][1][0] = "Uppercase Zeta";
      symbolButtonNames[1][1][1] = "Uppercase Eta";
      symbolButtonNames[1][1][2] = "Uppercase Theta";
      symbolButtonNames[1][1][3] = "Uppercase Iota";
      symbolButtonNames[1][1][4] = "Uppercase Kappa";
      symbolButtonNames[1][2][0] = "Uppercase Lambda";
      symbolButtonNames[1][2][1] = "Uppercase Mu";
      symbolButtonNames[1][2][2] = "Uppercase Nu";
      symbolButtonNames[1][2][3] = "Uppercase Xi";
      symbolButtonNames[1][2][4] = "Uppercase Omicron";
      symbolButtonNames[1][3][0] = "Uppercase Pi";
      symbolButtonNames[1][3][1] = "Uppercase Rho";
      symbolButtonNames[1][3][2] = "Uppercase Sigma";
      symbolButtonNames[1][3][3] = "Uppercase Tau";
      symbolButtonNames[1][3][4] = "Uppercase Upsilon";
      symbolButtonNames[1][4][0] = "Uppercase Phi";
      symbolButtonNames[1][4][1] = "Uppercase Chi";
      symbolButtonNames[1][4][2] = "Uppercase Psi";
      symbolButtonNames[1][4][3] = "Uppercase Omega";
      symbolButtonNames[2][0][0] = "Summation"; //functions
      symbolButtonNames[2][0][1] = "Integral";
      symbolButtonNames[2][0][2] = "Absolute Value";
      symbolButtonNames[2][0][3] = "Floor";
      symbolButtonNames[2][0][4] = "Ceiling";
      symbolButtonNames[2][1][0] = "Exponent";
      symbolButtonNames[2][1][1] = "Square Root";
      symbolButtonNames[2][1][2] = "Nth Root";
      symbolButtonNames[2][1][3] = "Product";
      symbolButtonNames[2][1][4] = "Coproduct";
      symbolButtonNames[2][2][0] = "Limit";
      symbolButtonNames[2][2][1] = "Infinity";
      symbolButtonNames[2][2][2] = "First Derivative";
      symbolButtonNames[2][2][3] = "Second Derivative";
      symbolButtonNames[2][2][4] = "Derivative";
      symbolButtonNames[2][3][0] = "Partial Derivative";
      symbolButtonNames[2][3][1] = "Logarithm";
      symbolButtonNames[2][3][2] = "Natural Logarithm";
      symbolButtonNames[3][0][0] = "Empty Set"; //sets
      symbolButtonNames[3][0][1] = "Union";
      symbolButtonNames[3][0][2] = "Intersection";
      symbolButtonNames[3][0][3] = "Difference";
      symbolButtonNames[3][0][4] = "Cartesian Product";
      symbolButtonNames[3][1][0] = "Complement";
      symbolButtonNames[3][1][1] = "Proper Subset";
      symbolButtonNames[3][1][2] = "Subset";
      symbolButtonNames[3][1][3] = "Not Subset";
      symbolButtonNames[3][1][4] = "Element";
      symbolButtonNames[3][2][0] = "Not Element";
      symbolButtonNames[3][2][1] = "Cardinality";
      symbolButtonNames[3][2][2] = "Set";
      symbolButtonNames[4][0][0] = "Not Equals"; //relation
      symbolButtonNames[4][0][1] = "Identity";
      symbolButtonNames[4][0][2] = "Approximately";
      symbolButtonNames[4][0][3] = "Similarity";
      symbolButtonNames[4][0][4] = "Proportionality";
      symbolButtonNames[4][1][0] = "Less Than Equal To";
      symbolButtonNames[4][1][1] = "Greater Than Equal To";
      symbolButtonNames[4][1][2] = "Much Less Than";
      symbolButtonNames[4][1][3] = "Much Greater Than";
      symbolButtonNames[5][0][0] = "Negation"; //logic
      symbolButtonNames[5][0][1] = "Logical Conjunction";
      symbolButtonNames[5][0][2] = "Logical Disjunction";
      symbolButtonNames[5][0][3] = "Logical Implication";
      symbolButtonNames[5][0][4] = "Logical Equivalence";
      symbolButtonNames[5][1][0] = "Exclusive Or";
      symbolButtonNames[5][1][1] = "For All";
      symbolButtonNames[5][1][2] = "There Exists";
      symbolButtonNames[5][1][3] = "There Exists One";
      symbolButtonNames[5][1][4] = "There Does Not Exist";
      symbolButtonNames[5][2][0] = "Therefore";
      symbolButtonNames[5][2][1] = "Because";
      symbolButtonNames[5][2][2] = "Tautology";
      symbolButtonNames[5][2][3] = "Contradiction";
      symbolButtonNames[6][0][0] = "Sine"; //trigonometry
      symbolButtonNames[6][0][1] = "Cosine";
      symbolButtonNames[6][0][2] = "Tangent";
      symbolButtonNames[6][0][3] = "Arcsine";
      symbolButtonNames[6][0][4] = "Arccosine";
      symbolButtonNames[6][1][0] = "Arctangent";
      symbolButtonNames[6][1][1] = "Cotangent";
      symbolButtonNames[6][1][2] = "Secant";
      symbolButtonNames[6][1][3] = "Cosecant";
      symbolButtonNames[6][1][4] = "Hyperbolic Sine";
      symbolButtonNames[6][2][0] = "Hyperbolic Cosine";
      symbolButtonNames[6][2][1] = "Hyperbolic Tangent";
      symbolButtonNames[6][2][2] = "Hyperbolic Cotangent";
      symbolButtonNames[6][2][3] = "Hyperbolic Secant";
      symbolButtonNames[6][2][4] = "Hyperbolic Cosecant";
      symbolButtonNames[7][0][0] = "Parallel"; //geometry
      symbolButtonNames[7][0][1] = "Orthogonal";
      symbolButtonNames[7][0][2] = "Angle";
      symbolButtonNames[7][0][3] = "Vector";
      symbolButtonNames[7][0][4] = "Matrix";
      symbolButtonNames[7][1][0] = "Transpose Matrix";
      symbolButtonNames[7][1][1] = "Inverse Matrix";
      symbolButtonNames[7][1][2] = "Orthogonal Matrix";
      symbolButtonNames[7][1][3] = "Dot Product";
      symbolButtonNames[7][1][4] = "Cross Product";
      symbolButtonNames[7][2][0] = "Determinant";
      symbolButtonNames[8][0][0] = "Real Numbers"; //number sets
      symbolButtonNames[8][0][1] = "Natural Numbers";
      symbolButtonNames[8][0][2] = "Integers";
      symbolButtonNames[8][0][3] = "Prime Numbers";
      symbolButtonNames[8][0][4] = "Rational Numbers";
      symbolButtonNames[8][1][0] = "Complex Numbers";
      symbolButtonNames[8][1][1] = "Quaternions";
      symbolButtonNames[8][1][2] = "Infinite Cardinals";
      symbolButtonNames[9][0][0] = "Plus or Minus"; //operators
      symbolButtonNames[9][0][1] = "Multiplication";
      symbolButtonNames[9][0][2] = "Times";
      symbolButtonNames[9][0][3] = "Division";
      symbolButtonNames[9][0][4] = "Fraction";
      symbolButtonNames[10][0][0] = "Pi"; //constants
      symbolButtonNames[10][0][1] = "Euler's Constant";
      symbolButtonNames[10][0][2] = "Golden Ratio";
      symbolButtonNames[10][0][3] = "Imaginary Unit";
      symbolButtonNames[11][0][0] = "Standard Deviation or Covariance"; //statistics
      symbolButtonNames[11][0][1] = "Correlation";
      symbolButtonNames[11][0][2] = "Percent";
      symbolButtonNames[11][0][3] = "Probability";
      symbolButtonNames[11][0][4] = "Variance";
      symbolButtonNames[11][1][0] = "Expected Value";
      symbolButtonNames[12][0][0] = "Combination"; //combinatorics
      symbolButtonNames[12][0][1] = "Permutation";
      symbolButtonNames[12][0][2] = "Factorial";
      symbolButtonNames[12][0][3] = "Primorial";
      symbolButtonNames[13][0][0] = "Hat"; //accents
      symbolButtonNames[13][0][1] = "Check";
      symbolButtonNames[13][0][2] = "Acuted";
      symbolButtonNames[13][0][3] = "Grave";
      symbolButtonNames[13][0][4] = "Bar";
      symbolButtonNames[13][1][0] = "Dot";
      symbolButtonNames[13][1][1] = "Double Dot";
      symbolButtonNames[13][1][2] = "Breve";
      symbolButtonNames[13][1][3] = "Tilde";
      
      symbolLaTeX[0][0][0] = "\\alpha"; //lowercase greek letters
      symbolLaTeX[0][0][1] = "\\beta";
      symbolLaTeX[0][0][2] = "\\gamma";
      symbolLaTeX[0][0][3] = "\\delta";
      symbolLaTeX[0][0][4] = "\\epsilon";
      symbolLaTeX[0][1][0] = "\\zeta";
      symbolLaTeX[0][1][1] = "\\eta";
      symbolLaTeX[0][1][2] = "\\theta";
      symbolLaTeX[0][1][3] = "\\iota";
      symbolLaTeX[0][1][4] = "\\kappa";
      symbolLaTeX[0][2][0] = "\\lambda";
      symbolLaTeX[0][2][1] = "\\mu";
      symbolLaTeX[0][2][2] = "\\nu";
      symbolLaTeX[0][2][3] = "\\xi";
      symbolLaTeX[0][2][4] = "\\omicron";
      symbolLaTeX[0][3][0] = "\\pi";
      symbolLaTeX[0][3][1] = "\\rho";
      symbolLaTeX[0][3][2] = "\\sigma";
      symbolLaTeX[0][3][3] = "\\tau";
      symbolLaTeX[0][3][4] = "\\upsilon";
      symbolLaTeX[0][4][0] = "\\phi";
      symbolLaTeX[0][4][1] = "\\chi";
      symbolLaTeX[0][4][2] = "\\psi";
      symbolLaTeX[0][4][3] = "\\omega";
      symbolLaTeX[1][0][0] = "\\Alpha"; //uppercase greek letters
      symbolLaTeX[1][0][1] = "\\Beta";
      symbolLaTeX[1][0][2] = "\\Gamma";
      symbolLaTeX[1][0][3] = "\\Delta";
      symbolLaTeX[1][0][4] = "\\Epsilon";
      symbolLaTeX[1][1][0] = "\\Zeta";
      symbolLaTeX[1][1][1] = "\\Eta";
      symbolLaTeX[1][1][2] = "\\Theta";
      symbolLaTeX[1][1][3] = "\\Iota";
      symbolLaTeX[1][1][4] = "\\Kappa";
      symbolLaTeX[1][2][0] = "\\Lambda";
      symbolLaTeX[1][2][1] = "\\Mu";
      symbolLaTeX[1][2][2] = "\\Nu";
      symbolLaTeX[1][2][3] = "\\Xi";
      symbolLaTeX[1][2][4] = "\\Omicron";
      symbolLaTeX[1][3][0] = "\\Pi";
      symbolLaTeX[1][3][1] = "\\Rho";
      symbolLaTeX[1][3][2] = "\\Sigma";
      symbolLaTeX[1][3][3] = "\\Tau";
      symbolLaTeX[1][3][4] = "\\Upsilon";
      symbolLaTeX[1][4][0] = "\\Phi";
      symbolLaTeX[1][4][1] = "\\Chi";
      symbolLaTeX[1][4][2] = "\\Psi";
      symbolLaTeX[1][4][3] = "\\Omega";
      symbolLaTeX[2][0][0] = "\\Sigma"; //functions
      symbolLaTeX[2][0][1] = "\\int";
      symbolLaTeX[2][0][2] = "\\vert\\phantom{5}\\vert";
      symbolLaTeX[2][0][3] = "\\lfloor\\phantom{5}\\rfloor";
      symbolLaTeX[2][0][4] = "\\lceil\\phantom{5}\\rceil";
      symbolLaTeX[2][1][0] = "a^n";
      symbolLaTeX[2][1][1] = "\\sqrt{\\phantom{5}}";
      symbolLaTeX[2][1][2] = "\\sqrt[n]{\\phantom{5}}";
      symbolLaTeX[2][1][3] = "\\prod";
      symbolLaTeX[2][1][4] = "\\coprod";
      symbolLaTeX[2][2][0] = "\\lim";
      symbolLaTeX[2][2][1] = "\\infty";
      symbolLaTeX[2][2][2] = "\\prime";
      symbolLaTeX[2][2][3] = "\\prime\\prime";
      symbolLaTeX[2][2][4] = "d";
      symbolLaTeX[2][3][0] = "\\partial";
      symbolLaTeX[2][3][1] = "\\log";
      symbolLaTeX[2][3][2] = "\\ln";
      symbolLaTeX[3][0][0] = "\\varnothing"; //sets
      symbolLaTeX[3][0][1] = "\\cup";
      symbolLaTeX[3][0][2] = "\\cap";
      symbolLaTeX[3][0][3] = "\\setminus";
      symbolLaTeX[3][0][4] = "\\times";
      symbolLaTeX[3][1][0] = "\\overline{\\phantom{5}}";
      symbolLaTeX[3][1][1] = "\\subset";
      symbolLaTeX[3][1][2] = "\\subseteq";
      symbolLaTeX[3][1][3] = "\\subsetneq";
      symbolLaTeX[3][1][4] = "\\in";
      symbolLaTeX[3][2][0] = "\\notin";
      symbolLaTeX[3][2][1] = "\\vert\\phantom{5}\\vert";
      symbolLaTeX[3][2][2] = "\\{\\phantom{5}\\}";
      symbolLaTeX[4][0][0] = "\\neq"; //relation
      symbolLaTeX[4][0][1] = "\\equiv";
      symbolLaTeX[4][0][2] = "\\approx";
      symbolLaTeX[4][0][3] = "\\sim";
      symbolLaTeX[4][0][4] = "\\propto";
      symbolLaTeX[4][1][0] = "\\leq";
      symbolLaTeX[4][1][1] = "\\geq";
      symbolLaTeX[4][1][2] = "\\ll";
      symbolLaTeX[4][1][3] = "\\gg";
      symbolLaTeX[5][0][0] = "\\neg"; //logic
      symbolLaTeX[5][0][1] = "\\land";
      symbolLaTeX[5][0][2] = "\\lor";
      symbolLaTeX[5][0][3] = "\\rightarrow";
      symbolLaTeX[5][0][4] = "\\leftrightarrow";
      symbolLaTeX[5][1][0] = "\\oplus";
      symbolLaTeX[5][1][1] = "\\forall";
      symbolLaTeX[5][1][2] = "\\exists";
      symbolLaTeX[5][1][3] = "\\exists!";
      symbolLaTeX[5][1][4] = "\\nexists";
      symbolLaTeX[5][2][0] = "\\therefore";
      symbolLaTeX[5][2][1] = "\\because";
      symbolLaTeX[5][2][2] = "\\top";
      symbolLaTeX[5][2][3] = "\\bot";
      symbolLaTeX[6][0][0] = "\\sin"; //trigonometry
      symbolLaTeX[6][0][1] = "\\cos";
      symbolLaTeX[6][0][2] = "\\tan";
      symbolLaTeX[6][0][3] = "\\arcsin";
      symbolLaTeX[6][0][4] = "\\arccos";
      symbolLaTeX[6][1][0] = "\\arctan";
      symbolLaTeX[6][1][1] = "\\cot";
      symbolLaTeX[6][1][2] = "\\sec";
      symbolLaTeX[6][1][3] = "\\csc";
      symbolLaTeX[6][1][4] = "\\sinh";
      symbolLaTeX[6][2][0] = "\\cosh";
      symbolLaTeX[6][2][1] = "\\tanh";
      symbolLaTeX[6][2][2] = "\\coth";
      symbolLaTeX[6][2][3] = "\\sech";
      symbolLaTeX[6][2][4] = "\\csch";
      symbolLaTeX[7][0][0] = "\\parallel"; //geometry
      symbolLaTeX[7][0][1] = "\\perp";
      symbolLaTeX[7][0][2] = "\\angle";
      symbolLaTeX[7][0][3] = "\\vec{\\phantom{5}}";
      symbolLaTeX[7][0][4] = "\\begin{bmatrix}\\phantom{5}\\end{bmatrix}";
      symbolLaTeX[7][1][0] = "\\begin{bmatrix}\\phantom{5}\\end{bmatrix}^{-1}";
      symbolLaTeX[7][1][1] = "\\begin{bmatrix}\\phantom{5}\\end{bmatrix}^T";
      symbolLaTeX[7][1][2] = "\\begin{bmatrix}\\phantom{5}\\end{bmatrix}^\\perp";
      symbolLaTeX[7][1][3] = "\\cdot";
      symbolLaTeX[7][1][4] = "\\times";
      symbolLaTeX[7][2][0] = "\\vert\\phantom{5}\\vert";
      symbolLaTeX[8][0][0] = "\\mathbb{R}"; //number sets
      symbolLaTeX[8][0][1] = "\\mathbb{N}";
      symbolLaTeX[8][0][2] = "\\mathbb{Z}";
      symbolLaTeX[8][0][3] = "\\mathbb{P}";
      symbolLaTeX[8][0][4] = "\\mathbb{Q}";
      symbolLaTeX[8][1][0] = "\\mathbb{C}";
      symbolLaTeX[8][1][1] = "\\mathbb{H}";
      symbolLaTeX[8][1][2] = "\\aleph";
      symbolLaTeX[9][0][0] = "\\pm"; //operations
      symbolLaTeX[9][0][1] = "\\cdot";
      symbolLaTeX[9][0][2] = "\\times";
      symbolLaTeX[9][0][3] = "\\div";
      symbolLaTeX[9][0][4] = "\\frac{a}{b}";
      symbolLaTeX[10][0][0] = "\\pi"; //constants
      symbolLaTeX[10][0][1] = "e";
      symbolLaTeX[10][0][2] = "\\varphi";
      symbolLaTeX[10][0][3] = "i";
      symbolLaTeX[11][0][0] = "\\sigma"; //statistics
      symbolLaTeX[11][0][1] = "\\rho";
      symbolLaTeX[11][0][2] = "\\%";
      symbolLaTeX[11][0][3] = "P";
      symbolLaTeX[11][0][4] = "V";
      symbolLaTeX[11][1][0] = "E";
      symbolLaTeX[12][0][0] = "\\binom{a}{b}"; //combinatorics
      symbolLaTeX[12][0][1] = "^nP_r";
      symbolLaTeX[12][0][2] = "!";
      symbolLaTeX[12][0][3] = "\\#";
      symbolLaTeX[13][0][0] = "\\hat{a}"; //accents
      symbolLaTeX[13][0][1] = "\\check{a}";
      symbolLaTeX[13][0][2] = "\\acute{a}";
      symbolLaTeX[13][0][3] = "\\grave{a}";
      symbolLaTeX[13][0][4] = "\\bar{a}";
      symbolLaTeX[13][1][0] = "\\dot{a}";
      symbolLaTeX[13][1][1] = "\\ddot{a}";
      symbolLaTeX[13][1][2] = "\\breve{a}";
      symbolLaTeX[13][1][3] = "\\tilde{a}";
      
      for (int i = 0; i < symbolLaTeX.length; i++) {
         
         symbolButtonPanelOptions[i] = new JPanel();
         symbolButtonPanelOptions[i].setLayout(new GridLayout(5, 5));
         
         for (int j = 0; j < symbolLaTeX[0].length; j++) {
            
            for(int k = 0; k < symbolLaTeX[0][0].length; k++) {
               
               if (symbolLaTeX[i][j][k] != null) {
                  symbolForm[i][j][k] = new TeXFormula(symbolLaTeX[i][j][k]);
                  symbolImage[i][j][k] = symbolForm[i][j][k].new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY)
                        .setSize(15).setWidth(TeXConstants.UNIT_PIXEL, 256f, TeXConstants.ALIGN_CENTER)
                        .setIsMaxWidth(true).setInterLineSpacing(TeXConstants.UNIT_PIXEL, 20f).build();
                  symbolButtons[i][j][k] = new JButton();
                  symbolButtons[i][j][k].setIcon(symbolImage[i][j][k]);
                  symbolButtons[i][j][k].setToolTipText(symbolButtonNames[i][j][k]);
                  symbolButtons[i][j][k].setActionCommand(symbolButtonNames[i][j][k]);
                  symbolButtons[i][j][k].setFocusPainted(false);
                  
                  symbolButtons[i][j][k].addActionListener(new ActionListener() {

                     @Override
                     public void actionPerformed(ActionEvent arg0) {
                        
                        input.addButtonFormula(arg0.getActionCommand().toString());
                        input.getPanel().requestFocus();

                     }

                  });

                  symbolButtonPanelOptions[i].add(symbolButtons[i][j][k]);
               }
            
            }
            
         }
         
         symbolButtonPanel.add(symbolButtonPanelOptions[i], groupButtons[i / 4][i % 4].getToolTipText());
            
      }
      
      inputPanel.add(description);
      inputLayer.add(input.getPanel());
      inputLayer.add(submit);
      inputLayer.add(giveUp);
      inputPanel.add(inputLayer);
      inputPanel.add(buttonPanel);
      
      return inputPanel;
      
   }
   
   public void getTextFocus() {

      input.getPanel().requestFocus();
      
   }
   
}
