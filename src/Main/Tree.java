package Main;

import Node.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Tree {

    public TreeNode getStartNode() {
        return startNode;
    }

    public void setStartNode(TreeNode startNode) {
        this.startNode = startNode;
        startNode.setLevel(0);
    }

    public Tree() {
    }

    TreeNode startNode;


    public Tree toAstProper(){
        Tree newTree = new Tree();
        TreeNode startNode = this.getStartNode();

        newTree.setStartNode(prog(startNode));
        return newTree;
    }


    public ProgNode prog(TreeNode Start){

        ProgNode newTreeStart = new ProgNode(startNode.prod,null,true);
        newTreeStart.setLevel(0);
        ClassListNodeAST classListAst = classList(startNode.getChildren().get(0));

        ArrayList<TreeNode> children = new ArrayList<TreeNode>();
        children.add(classListAst);
        FuncDefListNodeAST fdlna = new FuncDefListNodeAST("funcDefList","funcDefList",null,false);
        ArrayList<TreeNode> fdnlalist = new ArrayList<TreeNode>();
        fdnlalist.addAll(funcDefRepeat(startNode.getChildren().get(1)));
        createFamily(fdlna,fdnlalist);
        children.add(fdlna);
        children.add(funcBody(startNode.getChildren().get(3)));

        createFamily(newTreeStart,children);

        return newTreeStart;

    }

    public TreeNode funcBody(TreeNode funcBody){

        return varDeclRepeatStatementRepeat(funcBody);
    }

    public TreeNode createFamily(TreeNode parent,ArrayList<TreeNode> children){
        if(children == null){
            return parent;
        }

//        for(int i = 0; i<children.size();i++){
//            if(children.get(i)==null){
//                children.remove(i);
//            }
//        }

        children.removeAll(Collections.singleton(null));

        if(children.size()==0){
            return parent;
        }
        for(int i = 0; i<children.size();i++){
            if(i<children.size()-1) {
                children.get(i).setSibling(children.get(i + 1));
            }
            children.get(i).setParent(parent);
//            children.get(i).setLevel(parent.getLevel()+1);
        }
        parent.setChildren(children);
        return parent;
    }


    public ClassListNodeAST classList(TreeNode classDeclRepeat){

        ClassListNodeAST clna = new ClassListNodeAST("classList",null,true);

        ArrayList<TreeNode> children = classDeclList(classDeclRepeat);

        createFamily(clna,children);
        return clna;
    }

    public ArrayList<TreeNode> classDeclList(TreeNode classDeclRepeat){

        ArrayList<TreeNode> classDeclList = new ArrayList<TreeNode>();
        //epsilon
        if(classDeclRepeat.children.size()==1){
            return classDeclList;
        }

        //repeat
        if(classDeclRepeat.children.size()==2){
            classDeclList.add(classDecl(classDeclRepeat.getChildren().get(0)));
            classDeclList.addAll(classDeclList(classDeclRepeat.getChildren().get(1)));
        }
        return classDeclList;
    }

    public ClassDeclNodeAST classDecl(TreeNode classDecl){

        ClassDeclNodeAST cdna = new ClassDeclNodeAST("classDecl",null,true);
        ArrayList<TreeNode> children = new ArrayList<TreeNode>();

        IdNodeAST idna = id(classDecl.getChildren().get(1));
        InherListAST ihla = inherlist(classDecl.getChildren().get(2));
        MembListAST mla = memblist(classDecl.getChildren().get(4));

        children.add(idna);
        children.add(ihla);
        children.add(mla);

        createFamily(cdna,children);
        return cdna;
    }

    public InherListAST inherlist(TreeNode classDeclOption){
        if(classDeclOption.getChildren().size()==1){
            InherListAST ihla = new InherListAST ("inherList","empty", null, true);
            return ihla;
        }
        ArrayList<TreeNode> children = inher(classDeclOption);
        InherListAST ihla = new InherListAST ("inherList", null, true);



        createFamily(ihla,children);
        return ihla;
    }

    public ArrayList<TreeNode> inher(TreeNode classDeclOption){
        ArrayList<TreeNode> inherList = new ArrayList<TreeNode>();
        //epsilon
        if(classDeclOption.children.size()==1){
            return inherList;
        }

        //repeat
        if(classDeclOption.children.size()==3){
            inherList.add(id(classDeclOption.getChildren().get(1)));
            inherList.addAll(inher(classDeclOption.getChildren().get(2)));
        }
        return inherList;
    }

    public MembListAST memblist(TreeNode varDeclFuncDecl){
        ArrayList<TreeNode> children = memb(varDeclFuncDecl);
        MembListAST mla = new MembListAST ("membList", null, true);
        createFamily(mla,children);
        return mla;
    }

    public ArrayList<TreeNode> memb(TreeNode varDeclFuncDecl){
        ArrayList<TreeNode> memblist = new ArrayList<TreeNode>();
        //epsilon
        if(varDeclFuncDecl.children.size()==1){
            return memblist;
        }

        //repeat
        if(varDeclFuncDecl.children.size()==2){
            if(varDeclFuncDecl.getChildren().get(1).getChildren().get(0).getProd().equals("arraySizeRepeat")){
                memblist.add(varDecl(varDeclFuncDecl));
                memblist.addAll(memb(varDeclFuncDecl.getChildren().get(1).getChildren().get(2)));
            }else if (varDeclFuncDecl.getChildren().get(1).getChildren().get(0).getProd().equals("funcDecl")){
                memblist.addAll(funcDeclList(varDeclFuncDecl));
            }


//            memblist.addAll(membDecl(varDeclFuncDecl));
        }
        return memblist;
    }

    public ArrayList<TreeNode> funcDeclList(TreeNode varDeclFuncDecl){
        ArrayList<TreeNode> funcDecllist = new ArrayList<TreeNode>();

        if(varDeclFuncDecl.children.size()==1){
            return funcDecllist;
        }
        if(varDeclFuncDecl.children.size()==2) {

            if (varDeclFuncDecl.getChildren().get(1).getProd().equals("varDeclPre")) {
                FuncDeclNodeAST fdna = new FuncDeclNodeAST("funcDecl", null, false);

                ArrayList<TreeNode> children = new ArrayList<TreeNode>();

                //children
                TypeNodeAST tna = type(varDeclFuncDecl.getChildren().get(0).getChildren().get(0));
                IdNodeAST idna = id(varDeclFuncDecl.getChildren().get(0).getChildren().get(1));
                FParamListNodeAST fplna = fParamList(varDeclFuncDecl.getChildren().get(1).getChildren().get(0).getChildren().get(1));


                children.add(tna);
                children.add(idna);
                children.add(fplna);

                createFamily(fdna, children);

                funcDecllist.add(fdna);
                funcDecllist.addAll(funcDeclList(varDeclFuncDecl.getChildren().get(1).getChildren().get(0).getChildren().get(4)));

            }
            if (varDeclFuncDecl.getChildren().get(1).getProd().equals("funcDecl")) {
                FuncDeclNodeAST fdna = new FuncDeclNodeAST("funcDecl", null, false);

                ArrayList<TreeNode> children = new ArrayList<TreeNode>();

                //children
                TypeNodeAST tna = type(varDeclFuncDecl.getChildren().get(0).getChildren().get(0));
                IdNodeAST idna = id(varDeclFuncDecl.getChildren().get(0).getChildren().get(1));
                FParamListNodeAST fplna = fParamList(varDeclFuncDecl.getChildren().get(1).getChildren().get(1));


                children.add(tna);
                children.add(idna);
                children.add(fplna);


                createFamily(fdna, children);

                funcDecllist.add(fdna);
                funcDecllist.addAll(funcDeclList(varDeclFuncDecl.getChildren().get(1).getChildren().get(4)));

            }
        }

        return funcDecllist;



//            if(varDeclFuncDecl.getChildren().get(1).getChildren().get(0).getProd().equals("arraySizeRepeat")){
//                memblist.add(varDecl(varDeclFuncDecl));
//                memblist.addAll(memb(varDeclFuncDecl.getChildren().get(1).getChildren().get(2)));
//            }else if (varDeclFuncDecl.getChildren().get(1).getChildren().get(0).getProd().equals("funcDecl")){
//                memblist.addAll(funcDecl(varDeclFuncDecl.getChildren().get(1).getChildren().get(0)));
//            }



    }

    public FParamListNodeAST fParamList(TreeNode fParams){

        FParamListNodeAST fplna= new FParamListNodeAST("fParamList",null, false);

        ArrayList<TreeNode> fParamList = fParamLists(fParams);

        createFamily(fplna,fParamList);
        return fplna;
    }

    public ArrayList<TreeNode> fParamLists(TreeNode fParams){

        ArrayList<TreeNode> fParamList = new ArrayList<TreeNode>();


        if(fParams.children.size()==1){
            return fParamList;
        }
        if(fParams.children.size()==2){
            fParamList.addAll(fParamLists(fParams.getChildren().get(0)));
            fParamList.addAll(fParamLists(fParams.getChildren().get(1)));
        }
        if(fParams.children.size()==3) {
            if(fParams.getChildren().get(0).getProd().equals("typeId")) {
                fParamList.add(fParam(fParams));
                fParamList.addAll(fParamLists(fParams.getChildren().get(2)));
            }else if(fParams.getChildren().get(0).getProd().equals(",")){
                fParamList.add(fParam(fParams));
            }
        }
        return fParamList;
    }

    public FParamNodeAST fParam(TreeNode fParams){

        ArrayList<TreeNode> children = new ArrayList<TreeNode>();

        FParamNodeAST fpna = new FParamNodeAST("fParam",null,false);

        //children

        TypeNodeAST tna;
        IdNodeAST idna;
        DimListNodeAST dlna;

        if(fParams.getChildren().get(0).getProd().equals("typeId")) {
            tna = type(fParams.getChildren().get(0).getChildren().get(0));
            idna = id(fParams.getChildren().get(0).getChildren().get(1));
            dlna = dimList(fParams.getChildren().get(1));

            children.add(tna);
            children.add(idna);
            children.add(dlna);
        }else if(fParams.getChildren().get(0).getProd().equals(",")){
            tna = type(fParams.getChildren().get(1).getChildren().get(0));
            idna = id(fParams.getChildren().get(1).getChildren().get(1));
            dlna = dimList(fParams.getChildren().get(2));

            children.add(tna);
            children.add(idna);
            children.add(dlna);
        }

        createFamily(fpna ,children);

        return fpna;
    }

    public VarDeclNodeAST varDecl(TreeNode varDeclFuncDecl){
        VarDeclNodeAST vdna = new VarDeclNodeAST("varDecl",null, false);

        ArrayList<TreeNode> children = new ArrayList<TreeNode>();

        //children
        TypeNodeAST tna = type(varDeclFuncDecl.getChildren().get(0).getChildren().get(0));
        IdNodeAST idna = id(varDeclFuncDecl.getChildren().get(0).getChildren().get(1));
        DimListNodeAST dlna = dimList(varDeclFuncDecl.getChildren().get(1).getChildren().get(0));

        children.add(tna);
        children.add(idna);
        children.add(dlna);

        createFamily(vdna,children);

        return vdna;
    }

    public TypeNodeAST type(TreeNode type){
        TypeNodeAST tna = new TypeNodeAST(type.getProd(),type.getToken().getLexeme(),null, false);
        return tna;
    }



    public DimListNodeAST dimList(TreeNode arraySizeRepeat){
        if(arraySizeRepeat.getChildren().size()==1){
            DimListNodeAST dlna = new DimListNodeAST( "dimList","empty", null, true);
            return dlna;
        }
        DimListNodeAST dlna = new DimListNodeAST( "dimList","dimList", null, true);


        ArrayList<TreeNode> children = numList(arraySizeRepeat);

        createFamily(dlna,children);
        return dlna;
    }

    public ArrayList<TreeNode> numList(TreeNode arraySizeRepeat){
        ArrayList<TreeNode> numList = new ArrayList<TreeNode>();
        //epsilon
        if(arraySizeRepeat.children.size()==1){
            return numList;
        }

        //repeat
        if(arraySizeRepeat.children.size()==2){
            numList.add(num(arraySizeRepeat.getChildren().get(0).getChildren().get(1)));
            numList.addAll(numList(arraySizeRepeat.getChildren().get(1)));
        }
        return numList;
    }

    public NumNodeAST num(TreeNode num){
        NumNodeAST nna = new NumNodeAST(num.getProd(),num.getToken().getLexeme().toString(),null, false);
        return nna;
    }


    public MembDeclNodeAST membDecl(TreeNode id){
        MembDeclNodeAST idna = new MembDeclNodeAST( id.getProd(),id.getToken().getLexeme().toString(), null, true);
        return idna;
    }

    public IdNodeAST id(TreeNode id){
        IdNodeAST idna = new IdNodeAST( id.getProd(),id.getToken().getLexeme().toString(), null, true);
        return idna;
    }


    public ArrayList<TreeNode> funcDefRepeat(TreeNode funcDefRepeat){

        ArrayList<TreeNode> funcDefList = new ArrayList<TreeNode>();

        if(funcDefRepeat.getChildren().size()==1){
            return  funcDefList;
        }
        funcDefList.add(funcDef(funcDefRepeat.getChildren().get(0)));
        funcDefList.addAll(funcDefRepeat(funcDefRepeat.getChildren().get(1)));
        return funcDefList;
    }

//    public ArrayList<TreeNode> funcDefRepeatList(TreeNode funcDefRepeat){
//        ArrayList<TreeNode> fdnal = new ArrayList<>();
//
//        if(funcDefRepeat.children.size()==1){
//            return fdnal;
//        }
//        if(funcDefRepeat.children.size()==2){
//            fdnal.add(funcDef(funcDefRepeat.getChildren().get(0)));
//        }
//        fdnal.addAll(funcDefRepeatList(funcDefRepeat.getChildren().get(1)));
//        return fdnal;
//    }

    public FuncDefNodeAST funcDef(TreeNode funcDef){
        FuncDefNodeAST fdna = new FuncDefNodeAST("funcDef",null,false);

        TypeNodeAST tna;
        IdNodeAST idna;
        ScopeSpecNodeAST ssna;
        FParamListNodeAST fplna;
        StatBlockNodeAST sbna;

        ArrayList<TreeNode> children = new ArrayList<TreeNode>();

        TreeNode head = funcDef.getChildren().get(0);
        TreeNode funcHeadOptionFactor  = head.getChildren().get(1);
        TreeNode funcHeadOption  =  funcHeadOptionFactor.getChildren().get(1);

        tna = type(head.getChildren().get(0).getChildren().get(0));

        if(funcHeadOption.children.size()==1){
            idna = id(funcHeadOptionFactor .getChildren().get(0));
            fplna = fParamList(head.getChildren().get(3));
            ssna = new ScopeSpecNodeAST("scopeSpec","empty",null,false);
            sbna = varDeclRepeatStatementRepeat(funcDef.getChildren().get(1));
        }else{
            idna = id(funcHeadOptionFactor .getChildren().get(0));
            ssna = scopespec(funcHeadOption.getChildren().get(1));
            fplna = fParamList(head.getChildren().get(3));
            sbna = varDeclRepeatStatementRepeat(funcDef.getChildren().get(1));
        }

        children.add(tna);
        children.add(ssna);
        children.add(idna);
        children.add(fplna);
        children.add(sbna);

        createFamily(fdna,children);
        return fdna;
    }



    public ScopeSpecNodeAST scopespec(TreeNode node){
        ScopeSpecNodeAST ssna = new ScopeSpecNodeAST("scopeSpec",null,false);
        ArrayList<TreeNode> children = new ArrayList<TreeNode>();
        children.add(id(node));
        createFamily(ssna, children);
        return ssna;
    }


    public StatBlockNodeAST varDeclRepeatStatementRepeat(TreeNode funcBody){
        StatBlockNodeAST sbna = new StatBlockNodeAST("statBlock",null,true);
        TreeNode varDeclRepeatStatementRepeat = funcBody.getChildren().get(1).getChildren().get(0);
        if(varDeclRepeatStatementRepeat.getProd().toString().equals("EPSILON")){

            StatBlockNodeAST sbnae = new StatBlockNodeAST("statBlock","empty",null,true);
            return sbnae;
        }else if (varDeclRepeatStatementRepeat.getProd().toString().equals("vadrsType")){
            ArrayList<TreeNode> children = new ArrayList<TreeNode>();
            children.addAll(statOvarDecl(varDeclRepeatStatementRepeat));
            createFamily(sbna, children);
            return sbna;


        }

        return sbna;
    }

    //returns list of statNodes and varDeclNodes
    public ArrayList<TreeNode> statOvarDecl(TreeNode vadrsType){
        ArrayList<TreeNode> sodna = new ArrayList<>();

        if(vadrsType.getChildren().get(0).getProd().equals("float")){
            sodna.add(varDecl2(vadrsType));
            if(vadrsType.getChildren().get(4).getChildren().get(0).getProd().equals("vadrsType")){
                sodna.addAll(statOvarDecl(vadrsType.getChildren().get(4).getChildren().get(0)));
            }
        }

        if(vadrsType.getChildren().get(0).getProd().equals("int")){
            sodna.add(varDecl2(vadrsType));
            if(vadrsType.getChildren().get(4).getChildren().get(0).getProd().equals("vadrsType")){
                sodna.addAll(statOvarDecl(vadrsType.getChildren().get(4).getChildren().get(0)));
            }
        }
        if(vadrsType.getChildren().get(0).getProd().equals("id")){
            if(vadrsType.getChildren().get(1).getChildren().get(0).getChildren().get(0).getProd().equals("id")){
                sodna.add(varDecl3(vadrsType));
                if(vadrsType.getChildren().get(1).getChildren().get(0).getChildren().get(3).getChildren().get(0).getProd().equals("vadrsType")){
                    sodna.addAll(statOvarDecl(vadrsType.getChildren().get(1).getChildren().get(0).getChildren().get(3).getChildren().get(0)));
                }
            }
            // will be assigOp
            if(vadrsType.getChildren().get(1).getChildren().get(0).getChildren().get(0).getProd().equals("functionCall")||vadrsType.getChildren().get(1).getChildren().get(0).getChildren().get(0).getProd().equals("variable")){
                sodna.addAll(statList(vadrsType));
            }

        }
            // other statements
        if(vadrsType.getChildren().get(0).getProd().equals("vadrsTypeStatement")){

            if(vadrsType.getChildren().get(0).getChildren().get(0).equals("if")){
                sodna.add(ifStat(vadrsType.getChildren().get(0)));
            }else  if(vadrsType.getChildren().get(0).getChildren().get(0).getProd().equals("for")){
                sodna.add(forStat(vadrsType.getChildren().get(0)));
            }else  if(vadrsType.getChildren().get(0).getChildren().get(0).getProd().equals("get")){
                sodna.add(getStat(vadrsType.getChildren().get(0)));
            }else  if(vadrsType.getChildren().get(0).getChildren().get(0).getProd().equals("put")){
                sodna.add(putStat(vadrsType.getChildren().get(0)));
            }else  if(vadrsType.getChildren().get(0).getChildren().get(0).getProd().equals("return")){
                sodna.add(returnStat(vadrsType.getChildren().get(0)));
            }

            sodna.addAll(statementRepeat(vadrsType.getChildren().get(1)));
        }

        return sodna;
    }

    //assignOp very complicated
    public ArrayList<TreeNode> statList(TreeNode vadrsType){

        ArrayList<TreeNode> statList = new ArrayList<TreeNode>();
        AssignStatNodeAST asna = new AssignStatNodeAST ("assignStat", "=",null, false);
        ArrayList<TreeNode> asnachildren = new ArrayList<TreeNode>();
        statList.add(asna);

        ArrayList<TreeNode> varElementList = new ArrayList<TreeNode>();
        if(vadrsType.getChildren().get(1).getChildren().get(0).getChildren().get(0).getProd().equals("functionCall")){
            statList.addAll(vadrsType02(asnachildren,varElementList,vadrsType.getChildren().get(0),vadrsType.getChildren().get(1).getChildren().get(0)));
        }

        if(vadrsType.getChildren().get(1).getChildren().get(0).getChildren().get(0).getProd().equals("variable")){
            statList.addAll(vadrsType03(asnachildren,varElementList,vadrsType.getChildren().get(0),vadrsType.getChildren().get(1).getChildren().get(0)));
        }


        createFamily(asna,asnachildren);




        return statList;
    }


    //not done
    //return var and expr
    public ArrayList<TreeNode> vadrsType02(ArrayList<TreeNode> asnachildren, ArrayList<TreeNode> varElementList, TreeNode idNode,TreeNode vadrsType02){
        ArrayList<TreeNode> statList = new ArrayList<TreeNode>();
        TreeNode functionCall = vadrsType02.getChildren().get(0);
        TreeNode aParams = vadrsType02.getChildren().get(1);
        TreeNode id = vadrsType02.getChildren().get(2);
        IdNodeAST idna = new IdNodeAST(id.getProd(), id.getToken().getLexeme().toString(),null,false);

        varElementList.add(fCall(idNode,aParams));

        if(vadrsType02.getChildren().get(3).getChildren().get(0).getProd().equals("functionCall")){
            statList.addAll(vadrsType02(asnachildren,varElementList,id,vadrsType02.getChildren().get(3).getChildren().get(0)));
        }

        if(vadrsType02.getChildren().get(3).getChildren().get(0).getProd().equals("variable")){
            statList.addAll(vadrsType03(asnachildren,varElementList,id,vadrsType02.getChildren().get(3).getChildren().get(0)));
        }


        return statList ;
    }


    public ArrayList<TreeNode> vadrsType03(ArrayList<TreeNode> asnachildren, ArrayList<TreeNode> varElementList,TreeNode idNode,TreeNode vadrsType03){

        ArrayList<TreeNode> statList = new ArrayList<TreeNode>();

        TreeNode variable = vadrsType03.getChildren().get(0);
        TreeNode vadrsType0VariableFactor = vadrsType03.getChildren().get(1);

        varElementList.add(dataMember(idNode, variable.getChildren().get(0)));

        if(vadrsType0VariableFactor.getChildren().get(0).getProd().equals(".")){

            TreeNode aParams = vadrsType0VariableFactor.getChildren().get(2).getChildren().get(0);
            TreeNode id = vadrsType0VariableFactor.getChildren().get(1);

            IdNodeAST idna = new IdNodeAST(id.getProd(), id.getToken().getLexeme().toString(),null,false);

            if(vadrsType0VariableFactor.getChildren().get(2).getChildren().get(0).getChildren().get(0).getProd().equals("functionCall")){
                statList.addAll(vadrsType02(asnachildren,varElementList,id,vadrsType0VariableFactor.getChildren().get(2).getChildren().get(0)));
            }

            if(vadrsType0VariableFactor.getChildren().get(2).getChildren().get(0).getChildren().get(0).getProd().equals("variable")){
                statList.addAll(vadrsType03(asnachildren,varElementList,id,vadrsType0VariableFactor.getChildren().get(2).getChildren().get(0)));
            }
        }

        if(vadrsType0VariableFactor.getChildren().get(0).getProd().equals("assignOp")){

            //varNode infront of assign
            VarNodeAST vdna = new VarNodeAST("var",null, false);
            createFamily(vdna,varElementList);

            asnachildren.add(vdna);
            asnachildren.add(expr(vadrsType0VariableFactor.getChildren().get(1)));

            statList.addAll(statementRepeat(vadrsType0VariableFactor.getChildren().get(3)));
        }


        return statList ;
    }

    public ArrayList<TreeNode> vadrsType0VariableFactor(TreeNode vadrsType){


        ArrayList<TreeNode> varExpr = new ArrayList<TreeNode>();
        VarNodeAST vdna = new VarNodeAST("var",null, false);
        ExprNodeAST ena = new ExprNodeAST("expr",null, false);
        ArrayList<TreeNode> varElementList = new ArrayList<TreeNode>();
        TreeNode vadrsType0203 = vadrsType.getChildren().get(1).getChildren().get(0);

        if(vadrsType0203.getChildren().get(0).getProd().equals("functionCall")){

            varElementList.add(fCall(vadrsType.getChildren().get(0),vadrsType.getChildren().get(1).getChildren().get(0).getChildren().get(0)));
            varElementList.addAll(varListFunc(vadrsType0203));
        }

        if(vadrsType0203.getChildren().get(0).getProd().equals("variable")){
            varElementList.add(dataMember(vadrsType.getChildren().get(0),vadrsType.getChildren().get(1).getChildren().get(0).getChildren().get(0)));
            varElementList.addAll(varListVariable(vadrsType0203.getChildren().get(1)));
        }


        return varExpr;
    }

    //not done
    //return var and expr
    public ArrayList<TreeNode> varExpr(TreeNode vadrsType){


        ArrayList<TreeNode> varExpr = new ArrayList<TreeNode>();
        VarNodeAST vdna = new VarNodeAST("var",null, false);
        ExprNodeAST ena = new ExprNodeAST("expr",null, false);
        ArrayList<TreeNode> varElementList = new ArrayList<TreeNode>();
        TreeNode vadrsType0203 = vadrsType.getChildren().get(1).getChildren().get(0);

        if(vadrsType0203.getChildren().get(0).getProd().equals("functionCall")){

            varElementList.add(fCall(vadrsType.getChildren().get(0),vadrsType.getChildren().get(1).getChildren().get(0).getChildren().get(0)));
            varElementList.addAll(varListFunc(vadrsType0203));
        }

        if(vadrsType0203.getChildren().get(0).getProd().equals("variable")){
            varElementList.add(dataMember(vadrsType.getChildren().get(0),vadrsType.getChildren().get(1).getChildren().get(0).getChildren().get(0)));
            varElementList.addAll(varListVariable(vadrsType0203.getChildren().get(1)));
        }


        return varExpr;
    }

    public ArrayList<TreeNode> varListFunc(TreeNode vadrsType0203){

        ArrayList<TreeNode> varElementList = new ArrayList<TreeNode>();

        if(vadrsType0203.getChildren().get(2).getChildren().get(0).getProd().equals("functionCall")){

            varElementList.add(fCall(vadrsType0203.getChildren().get(2),vadrsType0203.getChildren().get(2).getChildren().get(0).getChildren().get(0)));
            varElementList.addAll(varListFunc(vadrsType0203.getChildren().get(3).getChildren().get(0).getChildren().get(0)));
        }

        if(vadrsType0203.getChildren().get(2).getChildren().get(0).getProd().equals("variable")){
//           varElementList.add(dataMember(vadrsType0203.getChildren().get(2),vadrsType0203.getChildren().get(2).getChildren().get(0).getChildren().get(0)));
        }

        return varElementList;
    }

    public ArrayList<TreeNode> varListVariable(TreeNode vadrsType0VariableFactor){

        ArrayList<TreeNode> varElementList = new ArrayList<TreeNode>();


        if(vadrsType0VariableFactor.getChildren().get(0).getProd().equals(".")){

            TreeNode vadrsType0203 =  vadrsType0VariableFactor.getChildren().get(2).getChildren().get(0);

            if(vadrsType0203.getChildren().get(0).getProd().equals("functionCall")){

                varElementList.add(fCall(vadrsType0VariableFactor.getChildren().get(1),vadrsType0203.getChildren().get(0).getChildren().get(0)));
                varElementList.addAll(varListFunc(vadrsType0203));
            }

            if(vadrsType0203.getChildren().get(0).getProd().equals("variable")){
//              varElementList.add(dataMember(vadrsType0203.getChildren().get(2),vadrsType0203.getChildren().get(2).getChildren().get(0).getChildren().get(0)));
            }
        }

        if(vadrsType0VariableFactor.getChildren().get(2).getProd().equals("assignOp")){
//            varElementList.add(dataMember(vadrsType0203.getChildren().get(2),vadrsType0203.getChildren().get(2).getChildren().get(0).getChildren().get(0)));
        }

        return varElementList;
    }

    public DataMemberNodeAST dataMember(TreeNode id,TreeNode indiceRepeat){

        DataMemberNodeAST dmna = new DataMemberNodeAST("dataMember",null,false);
        ArrayList<TreeNode> children = new ArrayList<TreeNode>();
        IdNodeAST idna = new IdNodeAST(id.getProd(),id.getToken().getLexeme(),null,false);
        IndexListNodeAST ilna = new IndexListNodeAST("indexList",null,false);


        if(indiceRepeat.getProd().equals("EPSILON")) {
            children.add(idna);
            createFamily(dmna, children);
        }else {

            ArrayList<TreeNode> arithExprlist = indexList(indiceRepeat);
            createFamily(ilna , arithExprlist);
            children.add(idna);
            children.add(ilna);
            createFamily(dmna, children);
        }

        return dmna;
    }

    public ArrayList<TreeNode> indexList(TreeNode indiceRepeat){
        ArrayList<TreeNode> arithExprlist = new ArrayList<TreeNode>();
        TreeNode indiceRepeatRepeat = indiceRepeat.getChildren().get(1);

        if(indiceRepeatRepeat.children.size()==1){
            arithExprlist.add(arithExpr(indiceRepeat.getChildren().get(0).getChildren().get(1)));
        }

        if(indiceRepeatRepeat.children.size()==2) {
            arithExprlist.add(arithExpr(indiceRepeat.getChildren().get(0).getChildren().get(1)));
            arithExprlist.addAll(indexList(indiceRepeat.getChildren().get(1)));
        }
        //children

        return arithExprlist;
    }


    //unfinished
    public TreeNode arithExpr(TreeNode arithExpr){

        TreeNode arithExprMod = arithExpr.getChildren().get(1);
        if(arithExprMod.getChildren().size()==1){

            return term(arithExpr.getChildren().get(0));

        }else if (arithExprMod.getChildren().size()==3){
            AddOpNodeAST aona = new AddOpNodeAST(arithExprMod.getChildren().get(0).getChildren().get(0).getProd(),null,false);
            ArrayList<TreeNode> children = new ArrayList<TreeNode>();
            children.add(term(arithExpr.getChildren().get(0)));
            children.add(arithExprMod(arithExprMod));
            createFamily(aona, children);
            return aona;
        }
        return null;
    }

    public TreeNode arithExprMod(TreeNode arithExprMod){

        TreeNode arithExprModRepeat = arithExprMod.getChildren().get(2);
        if(arithExprModRepeat.getChildren().size()==1){

            return term(arithExprMod.getChildren().get(1));

        }else if (arithExprModRepeat.getChildren().size()==3){
            AddOpNodeAST aona = new AddOpNodeAST(arithExprMod.getChildren().get(0).getChildren().get(0).getProd(),null,false);
            ArrayList<TreeNode> children = new ArrayList<TreeNode>();
            children.add(term(arithExprMod.getChildren().get(1)));
            children.add(arithExprMod(arithExprMod.getChildren().get(2)));
            createFamily(aona, children);
            return aona;
        }

        return null;
    }

    public FCallNodeAST fCall(TreeNode id,TreeNode aParams){

        FCallNodeAST fcna = new FCallNodeAST("fCall",null,false);
        ArrayList<TreeNode> children = new ArrayList<TreeNode>();
        IdNodeAST idna = new IdNodeAST(id.getProd(),id.getToken().getLexeme().toString(),null,false);
        AParamNodeAST apna = aParam(aParams);
        children.add(id);
        children.add(apna);
        createFamily(fcna, children);

        return fcna;
    }

    public AParamNodeAST aParam(TreeNode aParams){



        AParamNodeAST apna = new AParamNodeAST("aParam",null,false);
        ArrayList<TreeNode> exprlist = new ArrayList<TreeNode>();

        if(aParams.children.size()==1){
            return apna;
        }

        if(aParams.children.size()==2) {
            exprlist.add(expr(aParams.getChildren().get(0)));
            exprlist.addAll(exprList(aParams.getChildren().get(1)));
        }
        //children

        createFamily(apna, exprlist);

        return apna;
    }

    public ArrayList<TreeNode>  exprList(TreeNode aParamsTailRepeat){

        ArrayList<TreeNode> exprlist = new ArrayList<TreeNode>();

        if(aParamsTailRepeat.children.size()==1){
            return exprlist;
        }

        if(aParamsTailRepeat.children.size()==2) {
            TreeNode aParamsTail = aParamsTailRepeat.getChildren().get(0);
            exprlist.add(expr(aParamsTail.getChildren().get(1)));
            exprlist.addAll(exprList(aParamsTailRepeat.getChildren().get(1)));
        }


        return exprlist;
    }

    public TreeNode expr(TreeNode expr){

        return relExpr(expr.getChildren().get(0));
    }

    public TreeNode relExpr(TreeNode relExpr){

        TreeNode relExprMod = relExpr.getChildren().get(1);
        ArrayList<TreeNode> children = new ArrayList<TreeNode>();
        if(relExprMod.getChildren().size()==1){
            return arithExpr(relExpr.getChildren().get(0));
        }else if(relExprMod.getChildren().size()==2){
            RelOpNodeAST rona = new RelOpNodeAST(relExprMod.getChildren().get(0).getChildren().get(0).getProd(),relExprMod.getChildren().get(0).getChildren().get(0).getToken().getLexeme(),null,false);
            children.add(arithExpr(relExpr.getChildren().get(0)));
            children.add(arithExpr(relExprMod.getChildren().get(1)));
            createFamily(rona,children);
            return rona;
        }
       return null;
    }

    public RelOpNodeAST relOp(TreeNode relOp){

        RelOpNodeAST rona = new RelOpNodeAST( relOp.getChildren().get(0).getProd(),  relOp.getChildren().get(0).getToken().getLexeme().toString(),null,false);
        return rona;
    }

    public TreeNode term(TreeNode term){


        if(term.getChildren().get(1).getChildren().size()==3){

            MultOpNodeAST mona = new MultOpNodeAST(term.getChildren().get(1).getChildren().get(0).getProd(),term.getChildren().get(1).getChildren().get(0).getChildren().get(0).getToken().getLexeme(), null, false);
            ArrayList<TreeNode> children = new ArrayList<TreeNode>();
            children.add(factor(term.getChildren().get(0)));
            children.add(termMod(term.getChildren().get(1)));
            createFamily(mona, children);
            return mona;

        }else if (term.getChildren().get(1).getChildren().size()==1){
            return factor(term.getChildren().get(0));
        }
        return null;
    }

    public TreeNode termMod(TreeNode termMod){

        TreeNode termModRepeat = termMod.getChildren().get(2);
        if(termModRepeat.getChildren().size()==1){

            return factor(termMod.getChildren().get(1));

        }else if (termModRepeat.getChildren().size()==3){
            MultOpNodeAST mona = new MultOpNodeAST(termMod.getChildren().get(0).getChildren().get(0).getProd(),null,false);
            ArrayList<TreeNode> children = new ArrayList<TreeNode>();
            children.add(term(termMod.getChildren().get(1)));
            children.add(termMod(termMod.getChildren().get(2)));
            createFamily(mona, children);
            return mona;
        }

        return null;
    }

    public TreeNode factor(TreeNode factor){

        switch (factor.getChildren().get(0).getProd()){
            case "(":  return arithExpr(factor.getChildren().get(1));
            case "floatNum":  FloatNumNodeAST fnna = new FloatNumNodeAST(factor.getChildren().get(0).getProd(),factor.getChildren().get(0).getToken().getLexeme(), null,false);
                return fnna;
            case "intNum":  IntNumNodeAST anna = new IntNumNodeAST(factor.getChildren().get(0).getProd(),factor.getChildren().get(0).getToken().getLexeme(), null,false);
                return anna ;
            case "not":  NotNodeAST notna = new NotNodeAST(factor.getChildren().get(0).getProd(), null,false);
                ArrayList<TreeNode> children = new ArrayList<TreeNode>();
                children.add(factor(factor.getChildren().get(1)));
                createFamily(notna,children);
                return notna ;
            case "sign":  SignNodeAST sna = new SignNodeAST(factor.getChildren().get(0).getChildren().get(0).getProd(),factor.getChildren().get(0).getChildren().get(0).getToken().getLexeme(), null,false);
                ArrayList<TreeNode> snchildren = new ArrayList<TreeNode>();
                snchildren.add(factor(factor.getChildren().get(1)));
                createFamily(sna,snchildren);
                return sna ;

            case "variableFunctionCallFactor":  return variableFunctionCallFactor(factor.getChildren().get(0));

        }
        return null;
    }

    public VarNodeAST variableFunctionCallFactor(TreeNode variableFunctionCallFactor){

        VarNodeAST vna = new VarNodeAST("var",null,false);
        ArrayList<TreeNode> varElementList = new ArrayList<TreeNode>();


        if(variableFunctionCallFactor.getChildren().get(1).getChildren().get(0).getProd().equals("variable")){

            varElementList.add(dataMember(variableFunctionCallFactor.getChildren().get(0),variableFunctionCallFactor.getChildren().get(1).getChildren().get(0).getChildren().get(0)));

        }else if(variableFunctionCallFactor.getChildren().get(1).getChildren().get(0).getProd().equals("functionCall")){

            varElementList.add(fCall(variableFunctionCallFactor.getChildren().get(0),variableFunctionCallFactor.getChildren().get(1).getChildren().get(0).getChildren().get(1)));
        }
        else if(variableFunctionCallFactor.getChildren().get(1).getChildren().get(0).getProd().equals("EPSILON")){

            varElementList.add(justId(variableFunctionCallFactor.getChildren().get(0)));
        }



        if(variableFunctionCallFactor.getChildren().get(2).getChildren().get(0).getProd().equals(".")){
            varElementList.addAll(idnestRepeatFactor(variableFunctionCallFactor.getChildren().get(2)));
        }

        createFamily(vna,varElementList);
        return vna;
    }

    public ArrayList<TreeNode> idnestRepeatFactor(TreeNode idnestRepeatFactor){

        TreeNode variableFunctionCallFactor = idnestRepeatFactor.getChildren().get(1);

        ArrayList<TreeNode> varElementList = new ArrayList<TreeNode>();


        if(variableFunctionCallFactor.getChildren().get(1).getChildren().get(0).getProd().equals("variable")){

            varElementList.add(dataMember(variableFunctionCallFactor.getChildren().get(0),variableFunctionCallFactor.getChildren().get(1).getChildren().get(0).getChildren().get(0)));

        }else if(variableFunctionCallFactor.getChildren().get(1).getChildren().get(0).getProd().equals("functionCall")){

            varElementList.add(fCall(variableFunctionCallFactor.getChildren().get(0),variableFunctionCallFactor.getChildren().get(1).getChildren().get(0).getChildren().get(1)));
        }
        else if(variableFunctionCallFactor.getChildren().get(1).getChildren().get(0).getProd().equals("EPSILON")){

            varElementList.add(justId(variableFunctionCallFactor.getChildren().get(0)));
        }

        return varElementList;
    }

    public IdNodeAST justId(TreeNode id){

        IdNodeAST idna = new IdNodeAST(id.getProd(),id.getToken().getLexeme().toString(),null,false);

        return idna ;
    }

    public AssignStatNodeAST assignStat(TreeNode assignStat){
        AssignStatNodeAST asna = new AssignStatNodeAST("assignStat","assignStat",null, false);

        ArrayList<TreeNode> children = new ArrayList<TreeNode>();
        ArrayList<TreeNode> varElementList = new ArrayList<TreeNode>();
        VarNodeAST vna = new VarNodeAST("var",null, false);

        varElementList.addAll(variableFactor(assignStat.getChildren().get(0)));
        createFamily(vna, varElementList);
        children.add(vna);
        children.add(expr(assignStat.getChildren().get(2)));

        createFamily(asna,children);

        return asna;
    }

    public ArrayList<TreeNode> variableFactor(TreeNode variableFactor){


        ArrayList<TreeNode> varElementList = new ArrayList<TreeNode>();
        TreeNode idNode = variableFactor.getChildren().get(0);
        TreeNode variableFactorFactor = variableFactor.getChildren().get(1);
        if( variableFactorFactor .getChildren().get(0).getProd().equals("functionCall")){
            varElementList.add( fCall(idNode, variableFactorFactor.getChildren().get(0).getChildren().get(1)));
            varElementList.addAll(variableFactor( variableFactorFactor.getChildren().get(2)));

        }else if ( variableFactorFactor.getChildren().get(0).getProd().equals("variable")){
            varElementList.add( dataMember(idNode, variableFactorFactor.getChildren().get(0).getChildren().get(0)));
             if ( variableFactorFactor .getChildren().get(1).getChildren().size()==2){
                 varElementList.addAll(variableFactor( variableFactorFactor .getChildren().get(1).getChildren().get(1)));
            }
        }

        return varElementList;
    }




    public IfStatNodeAST ifStat(TreeNode statement){
        IfStatNodeAST isna = new IfStatNodeAST("if",null, false);

        ArrayList<TreeNode> children = new ArrayList<TreeNode>();

        //children

        StatBlockNodeAST sbna1 = statBlock(statement.getChildren().get(0).getChildren().get(5));
        StatBlockNodeAST sbna2 = statBlock(statement.getChildren().get(0).getChildren().get(7));
        children.add(expr(statement.getChildren().get(0).getChildren().get(2)));
        children.add(sbna1);
        children.add(sbna2);

        createFamily(isna,children);

        return isna;

}

    public StatBlockNodeAST statBlock(TreeNode statBlock){

        StatBlockNodeAST sbna = new StatBlockNodeAST("statBlock", null,false);
        ArrayList<TreeNode> children = new ArrayList<TreeNode>();


        if(statBlock.getChildren().get(0).getProd().equals("EPSILON")){

        }else if(statBlock.getChildren().get(0).getProd().equals("{")){
            children.addAll(statementRepeat(statBlock.getChildren().get(1)));

        } else if(statBlock.getChildren().get(0).getProd().equals("statement")){
            children.add(statement(statBlock.getChildren().get(1)));

        }

        createFamily(sbna, children);


        return sbna;

    }

    public ArrayList<TreeNode>  statementRepeat(TreeNode statementRepeat){


        ArrayList<TreeNode> statOrVarDeclList = new ArrayList<TreeNode>();


         if(statementRepeat.getChildren().size()==2){
            statOrVarDeclList.add(statement(statementRepeat.getChildren().get(0)));
            statOrVarDeclList.addAll(statementRepeat(statementRepeat.getChildren().get(1)));
        }

        return statOrVarDeclList;

    }
        // return 1 statement (stat or var decl)
    public TreeNode statement(TreeNode statement){

        if(statement.getChildren().get(0).equals("if")){
            return ifStat(statement);
        }else  if(statement.getChildren().get(0).getProd().equals("for")){
            return forStat(statement);
        }else  if(statement.getChildren().get(0).getProd().equals("get")){
            return getStat(statement);
        }else  if(statement.getChildren().get(0).getProd().equals("put")){
            return putStat(statement);
        }else  if(statement.getChildren().get(0).getProd().equals("return")){
            return returnStat(statement);
        }else  if(statement.getChildren().get(0).getProd().equals("assignStat")){
            return assignStat(statement.getChildren().get(0));
        }
        return null;
    }

    public ForStatNodeAST forStat(TreeNode statement){
        ForStatNodeAST fsna = new ForStatNodeAST("for",null, false);

        ArrayList<TreeNode> children = new ArrayList<TreeNode>();

        //children
        TypeNodeAST tna = type(statement.getChildren().get(2).getChildren().get(0));
        IdNodeAST idna = id(statement.getChildren().get(2).getChildren().get(1));
        AssignStatNodeAST assignStat = assignStat(statement.getChildren().get(8));
        StatBlockNodeAST statBlock = statBlock(statement.getChildren().get(10));
        children.add(tna);
        children.add(idna);
        children.add(expr(statement.getChildren().get(4)));
        children.add(relExpr(statement.getChildren().get(6)));
        children.add(assignStat);
        children.add(statBlock);

        createFamily(fsna,children);

        return fsna;
    }


    public GetStatNodeAST getStat(TreeNode statement){
        GetStatNodeAST gsna = new GetStatNodeAST("getStat",null, false);

        ArrayList<TreeNode> children = new ArrayList<TreeNode>();
        ArrayList<TreeNode> varElementList = new ArrayList<TreeNode>();

        VarNodeAST vna = new VarNodeAST("var",null,false);
        varElementList.addAll(variableFactor(statement.getChildren().get(2)));
        createFamily(vna,varElementList);
        children.add(vna);
        createFamily(gsna,children);

        return gsna;
    }

    public PutStatNodeAST putStat(TreeNode statement){
        PutStatNodeAST psna = new PutStatNodeAST("putStat",null, false);

        ArrayList<TreeNode> children = new ArrayList<TreeNode>();

        children.add(expr(statement.getChildren().get(2)));
        createFamily(psna,children);

        return psna;
    }


    public ReturnStatNodeAST returnStat(TreeNode statement){
        ReturnStatNodeAST rsna = new ReturnStatNodeAST("returnStat",null, false);

        ArrayList<TreeNode> children = new ArrayList<TreeNode>();

        children.add(expr(statement.getChildren().get(2)));
        createFamily(rsna,children);

        return rsna;
    }



    public VarDeclNodeAST varDecl2(TreeNode vadrsType){
        VarDeclNodeAST vdna = new VarDeclNodeAST("varDecl",null, false);

        ArrayList<TreeNode> children = new ArrayList<TreeNode>();

        //children
        TypeNodeAST tna = type(vadrsType.getChildren().get(0));
        IdNodeAST idna = id(vadrsType.getChildren().get(1));
        DimListNodeAST dlna = dimList(vadrsType.getChildren().get(2));

        children.add(tna);
        children.add(idna);
        children.add(dlna);

        createFamily(vdna,children);

        return vdna;
    }

    public VarDeclNodeAST varDecl3(TreeNode vadrsType){
        VarDeclNodeAST vdna = new VarDeclNodeAST("varDecl",null, false);

        ArrayList<TreeNode> children = new ArrayList<TreeNode>();

        //children
        TypeNodeAST tna = type(vadrsType.getChildren().get(0));
        IdNodeAST idna = id(vadrsType.getChildren().get(1).getChildren().get(0).getChildren().get(0));
        DimListNodeAST dlna = dimList(vadrsType.getChildren().get(1).getChildren().get(0).getChildren().get(1));

        children.add(tna);
        children.add(idna);
        children.add(dlna);

        createFamily(vdna,children);

        return vdna;
    }

}
