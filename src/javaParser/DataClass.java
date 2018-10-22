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
import java.util.HashMap;
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
        HashMap<String,Boolean> map = new HashMap<>();


        public void visit(ClassOrInterfaceDeclaration c, Object arg) {

            for (MethodDeclaration method : c.getMethods()) {
                Optional<BlockStmt> mBlock = method.getBody();
                NodeList<Statement> methStatements = mBlock.get().getStatements();
                if (methStatements.size() < 3) {
                    map.put(method.toString(),true);
                } else {
                    map.put(method.toString(),false);
                }

            }
            List<MethodDeclaration> methods = c.getMethods();

            for (MethodDeclaration m : methods) {


                if (m.getNameAsString().length() > 3) {
                    String prefix = m.getNameAsString().substring(0, 3);
                    System.out.println(prefix);
                    if ((!prefix.contains("get")) || (!prefix.contains("set")) || (!prefix.contains("to")) || (!prefix.contains("is"))) {
                        map.put(m.getNameAsString(),false);
                    } else {
                        map.put(m.getNameAsString(),true);
                        System.out.println("true because of this section");
                    }
                }
            }


            for(MethodDeclaration m : methods) {
                if(map.get(m.toString()).booleanValue()==true){
                    dataClass = true;
                }
                else{
                    dataClass = false;
                }
            }

            if(dataClass==true){
                System.out.println("Fail. This is a data class");
            }else{
                System.out.println("Pass.");
            }

        }

    }
}

