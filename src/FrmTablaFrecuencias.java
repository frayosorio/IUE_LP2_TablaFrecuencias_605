import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class FrmTablaFrecuencias extends JFrame {

    // variables globales
    private String[] respuestas = new String[1000];
    private int totalRespuestas = -1;

    private JComboBox cmbRespuesta;
    JList lstRespuestas;

    // metodo constructor
    public FrmTablaFrecuencias() {
        // definir tamaño de la ventana
        setSize(500, 500);
        // asignar titulo
        setTitle("Tabla de Frecuencias");
        // operación de cierre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // quitar distribucion
        setLayout(null);

        JLabel lblPregunta = new JLabel("Pregunta:");
        lblPregunta.setBounds(10, 10, 100, 25);
        add(lblPregunta);

        JTextArea txtPregunta = new JTextArea(
                "¿Cómo considera la calidad de la señal de internet que entra al barrio?");
        txtPregunta.setBounds(120, 10, 310, 50);
        txtPregunta.setLineWrap(true);
        txtPregunta.setEditable(false);
        add(txtPregunta);

        JLabel lblRespuesta = new JLabel("Respuesta:");
        lblRespuesta.setBounds(10, 60, 100, 25);
        add(lblRespuesta);

        // crear una lista desplegable
        cmbRespuesta = new JComboBox();
        cmbRespuesta.setBounds(120, 60, 100, 25);
        add(cmbRespuesta);

        // definir el modelo de datos de la lista desplegable
        String[] variable = { "Excelente", "Buena", "Regular", "Mala" };
        DefaultComboBoxModel dcm = new DefaultComboBoxModel(variable);
        cmbRespuesta.setModel(dcm);

        JButton btnAgregar = new JButton(">>");
        btnAgregar.setBounds(120, 95, 100, 25);
        add(btnAgregar);

        JButton btnQuitar = new JButton("<<");
        btnQuitar.setBounds(120, 130, 100, 25);
        add(btnQuitar);

        // declarar la lista que mostrará todas las respuestas agregadas
        lstRespuestas = new JList();
        JScrollPane spRespuestas = new JScrollPane(lstRespuestas);
        spRespuestas.setBounds(230, 60, 100, 100);
        add(spRespuestas);

        JButton btnCalcular = new JButton("Calcular Frecuencias");
        btnCalcular.setBounds(10, 170, 200, 25);
        add(btnCalcular);

        // declarar la tabla donde se mostrará el análisis de las frecuencias de las
        // respuestas
        JTable tblFrecuencias = new JTable();
        JScrollPane spFrecuencias = new JScrollPane(tblFrecuencias);
        spFrecuencias.setBounds(10, 205, 470, 200);
        add(spFrecuencias);

        // definir el contenido inicial de la tabla
        String[] encabezados = { "Variable",
                "Frecuencia absoluta (f)",
                "Frecuencia acumulada (F)",
                "Frecuencia relativa (fr)",
                "Frecuencia porcentual (%f)" };

        String[][] datosFrecuencias = new String[variable.length][encabezados.length];

        for (int i = 0; i < variable.length; i++) {
            datosFrecuencias[i][0] = variable[i];
        }

        DefaultTableModel dtm = new DefaultTableModel(datosFrecuencias, encabezados);
        tblFrecuencias.setModel(dtm);

        // eventos
        btnAgregar.addActionListener(evento -> {
            agregarRespuesta();
        });

        btnQuitar.addActionListener(evento -> {
            quitarRespuesta();
        });

    }

    private void agregarRespuesta() {
        if (totalRespuestas < 999) {
            totalRespuestas++;
            respuestas[totalRespuestas] = cmbRespuesta.getSelectedItem().toString();
            mostrarRespuestas();
        } else {
            JOptionPane.showMessageDialog(null, "Ya no se pueden agregar más respuestas");
        }
    }

    private void mostrarRespuestas() {
        String[] respuestasAMostrar = new String[totalRespuestas + 1];
        // recorrer todas las respuestas agregadas
        for (int i = 0; i <= totalRespuestas; i++) {
            respuestasAMostrar[i] = respuestas[totalRespuestas - i];
        }
        lstRespuestas.setListData(respuestasAMostrar);
    }

    private void quitarRespuesta() {

    }

}
