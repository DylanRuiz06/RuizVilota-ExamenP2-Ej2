import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SoldadoVirtualGUI {
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JComboBox<String> comboBox3;
    private JButton registarSoldadoButton;
    private JTable table1;
    private JPanel jp;
    private JButton buscarButton;
    private JButton calcularButton;
    private JTextArea textArea3;
    private JButton ordenarButton;
    private JButton buscarButton1;

    private SoldadoVirtualLista listaSoldados = new SoldadoVirtualLista();
    private DefaultTableModel tableModel;

    public SoldadoVirtualGUI() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Id");
        tableModel.addColumn("Alias");
        tableModel.addColumn("Tecnología Especial");
        tableModel.addColumn("Nivel Virtualidad");
        tableModel.addColumn("Dimensión");
        table1.setModel(tableModel);

        registarSoldadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(textArea1.getText().trim());
                    String alias = textArea2.getText().trim();
                    String tech = (String) comboBox1.getSelectedItem();
                    int nivel = Integer.parseInt((String) comboBox2.getSelectedItem());
                    String dim = (String) comboBox3.getSelectedItem();

                    if (alias.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ingrese un alias.");
                        return;
                    }

                    SoldadoVirtual sv = new SoldadoVirtual(id, alias, tech, nivel, dim);

                    if (listaSoldados.agregarSoldado(sv)) {
                        actualizarTabla(listaSoldados.toArrayList());
                        limpiarCampos(); //metodo para limpiar las casillas y reiniciar las selection boxs
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número.");
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(textArea1.getText().trim()); //obtener el id de la caja de texto
                    SoldadoVirtual soldadovirtual = listaSoldados.buscarSoldadoPorId(id);  //llamar al método

                    if (soldadovirtual == null) { //comprobar si la lista está vacía
                        JOptionPane.showMessageDialog(null, "Soldado no encontrado.");
                        limpiarCampos();
                    } else {
                        textArea1.setText(String.valueOf(soldadovirtual.getId()));
                        textArea2.setText(soldadovirtual.getAlias());
                        comboBox1.setSelectedItem(soldadovirtual.getTecnologiaEspecial());
                        comboBox2.setSelectedItem(String.valueOf(soldadovirtual.getNivelVirtualidad()));
                        comboBox3.setSelectedItem(soldadovirtual.getDimensionOperativa());

                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un ID numérico válido para buscar.");
                }
            }
        });

        buscarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ttech = (String) comboBox1.getSelectedItem(); //obtener la tecnología del soldado a través del combobox
                ArrayList<SoldadoVirtual> filtrados = listaSoldados.filtrarPorTecnologia(ttech); //mostrar en la tabla a los soldados con la tecnología deseada

                if (filtrados.isEmpty()) { //comprobar si no hay soldados con la tecnología seleccionada
                    JOptionPane.showMessageDialog(null, "No hay soldados con la tecnología '" + ttech);
                }
                actualizarTabla(filtrados);
            }
        });

        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<SoldadoVirtual> Soldados = listaSoldados.toArrayList(); //utilizar el array donde se encuentra la información de la lista
                if (Soldados.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay soldados registrados."); //comprobación si la lista está vacía
                    actualizarTabla(new ArrayList<>()); //se deja la lista tal cual está
                } else {
                    listaSoldados.ordenarPorNivelSeleccion(Soldados); // se llama al método para ordenar a los soldados
                    actualizarTabla(Soldados); //se mprime la tabla en el nuevo orden
                }
            }
        });

        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder resultados = new StringBuilder("Conteo de soldados por dimensión\n"); //crear un strngbuilder donde se almacenrá el conteo de soldados
                for (int i = 0; i < comboBox3.getItemCount(); i++) {
                    String dimension = (String) comboBox3.getItemAt(i); // se obtiene la dimensión actual del soldado
                    int count = listaSoldados.contarPorDimension(dimension);  // se contabiliza cuantas veces se registra cada dimensión
                    resultados.append(dimension).append(": ").append(count).append("\n"); //se concatena la información y se la muestra en el textarea
                }
                textArea3.setText(resultados.toString());
            }
        });
    }

    //se cre un método para actualizar la tabla e imprimir los valores
    private void actualizarTabla(ArrayList<SoldadoVirtual> soldados) {
        tableModel.setRowCount(0);
        for (SoldadoVirtual soldadvirtuall : soldados) {
            tableModel.addRow(new Object[]{
                    soldadvirtuall.getId(),
                    soldadvirtuall.getAlias(),
                    soldadvirtuall.getTecnologiaEspecial(),
                    soldadvirtuall.getNivelVirtualidad(),
                    soldadvirtuall.getDimensionOperativa()
            });
        }
    }

    //al tener que limpiar los datos de los campos y resetear los combobox a la primera opción repetidas veces, se crea un método para realizar esta acción automáticamente
    private void limpiarCampos() {
        textArea1.setText("");
        textArea2.setText("");
        comboBox1.setSelectedIndex(0);
        comboBox2.setSelectedIndex(0);
        comboBox3.setSelectedIndex(0);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Registro de Soldados Virtuales");
                frame.setContentPane(new SoldadoVirtualGUI().jp);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
