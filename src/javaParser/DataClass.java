package javaParser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class DataClass {

    public void run(File file) throws Exception {

        CompilationUnit cu = JavaParser.parse(file);
        DataClassVisitor dc = new DataClassVisitor();

        cu.accept(dc, null);
    }


        private static class DataClassVisitor extends VoidVisitorAdapter {

            boolean dataClass = false;

            public void visit(ClassOrInterfaceDeclaration c, Object arg) {

                for (MethodDeclaration method : c.getMethods()) {
                    Optional<BlockStmt> mBlock = method.getBody();
                    NodeList<Statement> methStatements = mBlock.get().getStatements();
                    if (methStatements.size() < 3) {
                        dataClass = true;
                    } else{
                        dataClass = false;
                    }

                    List<MethodDeclaration> methods = c.getMethods();

                    for (MethodDeclaration m : methods) {

                        String prefix = m.getNameAsString().substring(0, 3);
                        if (!prefix.contains("get") || !prefix.contains("set") || !prefix.contains("to") || !prefix.contains("is")) {
                            dataClass = false;
                        } else {
                            dataClass = true;
                        }
                    }

                }


                if (dataClass == true) {
                    System.out.println(" is a Data Class");
                } else {
                    System.out.println("Pass - ");
                }
            }

            public boolean getDataClass() {
                return dataClass;
            }
        }
    }

