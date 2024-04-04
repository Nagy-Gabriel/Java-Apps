package ex1;

public class Carte {

        private String titlu;
        private String autorul;
        private int anul;

        public Carte(String titlu, String autorul, int anul) {
            this.titlu = titlu;
            this.autorul = autorul;
            this.anul = anul;
        }

        public String getTitlu() {
            return titlu;
        }

        public String getAutorul() {
            return autorul;
        }

        public int getAnul() {
            return anul;
        }

        @Override
        public String toString() {
            return "Titlu: " + titlu + ", Autor: " + autorul + ", Anul: " + anul;
        }

}
