# Puzzle de rodas (3 anéis, 10 veículos)

## Organização das pastas

```
puzzle-rodas/
├── pom.xml                         (Maven + JUnit 5)
└── src/
    ├── main/java/com/example/
    │   ├── Main.java               (consola: ler comandos e mostrar)
    │   ├── modelo/                 (os objetos)
    │   │   ├── Matricula.java
    │   │   ├── Veiculo.java
    │   │   ├── Carro.java
    │   │   ├── Mota.java
    │   │   └── Barco.java
    │   ├── estrutura/              (listas e nós)
    │   │   ├── Lugar.java
    │   │   ├── No.java
    │   │   └── Anel.java
    │   └── jogo/                   (regras do puzzle)
    │       └── Puzzle.java
    └── test/java/com/example/      (mesma estrutura, um teste por classe)
        ├── modelo/     MatriculaTest, VeiculoTest, CarroTest, MotaTest, BarcoTest
        ├── estrutura/  LugarTest, NoTest, AnelTest
        └── jogo/       PuzzleTest
```

## VS Code
1. Abre a pasta que tem o pom.xml. O VS Code importa o projeto e descarrega o JUnit sozinho.
2. Programa: abre src/main/java/com/example/Main.java e carrega em Run.
3. Testes: separador Testing (frasco) -> Run Tests.

## Terminal (se tiveres Maven instalado)
    mvn test
