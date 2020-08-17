package fg331.com.Automation;

import fg331.com.KeyManager.KeyManager;
import fg331.com.Main.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Auto {
    private final KeyManager keyManager;
    Game game;
    int begX;
    int begY;
    int endX;
    int endY;
    int offsetY = 0;
    int turns = 0;
    private boolean[][] grid = new boolean[10][20];
    private ArrayList<Integer> actions = new ArrayList<>();
    private ArrayList<Integer> tempActions = new ArrayList<>();
    private Point[] points = new Point[4];
    private Point[] blockPoints = new Point[4];
    private Point[] blockPointsTemp = new Point[4];
    private int turnsTemp = 0;
    private boolean shift = true;
    private int cascadeTemp = 0;
    private boolean tempBlockArrayInit = false;
    private int temporalLobe = 0;
    private int globalBegX, globalBegY;
    private boolean positionNotFound = true;
    private int spacingTemp = 0;
    private int heightTemp = 0;
    private int maxTemp = 0;


    //TODO трябва да оправя откриването на правилно завъртане за парчетата
    //TODO да се спре ако стигне до друго парче ама защо открива пътя да е в друго парче

    public Auto(Game game, KeyManager keyManager) {
        this.game = game;
        this.keyManager = keyManager;
    }

    public void render() {
//        game.g.drawImage(Assets.gridLine, game.leftOffset, game.topOffset, null);

        for (int i = 0; i < 4; i++) {
            game.g.setColor(new Color(241, 224, 50));
            if (blockPoints[i] != null) {
                game.g.drawRect(game.leftOffset + blockPoints[i].x * game.WIDTH, game.heightOffset + blockPoints[i].y * game.HEIGHT + game.topOffset, game.WIDTH, game.HEIGHT);
            }
        }
    }

    //TODO нова матрица 20х10 от булеви която презентира дали вече има пукче

    public void endings() {
        //TODO да мине през всички възможни движение и когато стигне край да оцени точките и да извърши движенията
        // да имам два листа един който запаметява движенията които стигнат да край и самото крайно положение
        // временен лист

        //TODO логика да открие пътя за движение и да възложи
        // във всяка от четирите посоки да мине зялото поле докато не открие позиция в която не създава празни полета
        // ако е невъзможно да не създаде то тогава да вземе най - краткото

        //TODO ако при проверката когато е завъртяно два пъти се получи най - добре още в началото(при създаване на парчето да се завърти)


//        for (int i = 0; i < 5; i++) {//проверката на позициите на парчето // във временни действия запаметявам сегашните движения и ако са по - добри ги вкарвам в движения
//
//            for (int j = 0; j < 4; j++) {
//                resetMatrix();
//
        //TODO switch за парчетата да /*-(ги итерита през матрицата)- */ да почне от правилното място
        // проверката да става ред по ред
//
//                createPoints();
//            }
//        }

        //TODO temp масив за точките така че да може да мине през всичко и след това да мине по най - добрия път

        //TODO проверката за след като мине всички възможности по широчина и след това височина и ДА СЕ ВЪРНЕ В НАЧАЛНА ПОЗИЦИЯ ЗАВЪРТЯНО
        // може и да не искам така да бъде
        //TODO но мога да направя така че ако за всеки х има място под парчетата да пропусна
        // масив от 10 булеви който ако за индекс е вярно - масива с индекс 'k' if(!stacker[k]) да продължи

        //TODO четворката излиза извън полето когато се създава и или придвижва
        //TODO при парчета които когато се завъртят са се вдигнали с едно не отговаря кода и остават едно пространство нагоре и трябва с автоматично движение от самата игра да се придвижат
        // направи променлива offset която да представлява колко а и дали едно парче е било свалено
        // отнася се за червено 'Л' във втора позиция

        //TODO нямаш план за ако наистина се получи да няма оптимално място за парчето

        //TODO изглежда сякаш не засича най - дясната колона

        //TODO трябва код за когато не открие място без празни блокчета под него
        //TODO всичко работи освен горното
        // кода за 'нямане' сигурно трябва да използва проверка където взима възможно най - малко въздух - да провери до долу защото може да има няколко реда въздух
        //TODO да има код да сравнява височината на на възможните оптимални места и да вземе най - плоското ниско положение
        // но първо направи кода за когато няма оптимални места

        //TODO не открива правилно но - ниска позиция
        //TODO при не оптимална позиция не открива по - ниската позиция
        //TODO ще бъде добре ако има не оптимална позиция много по - ниско отколкот една оптимална да изберете първото

        //

        actions = new ArrayList<>();
        tempActions = new ArrayList<>();

        System.out.println("Created points");
        createPoints();
        turns = 0;
        turnsTemp = 0;
        heightTemp = 0;
        maxTemp = 0;
        positionNotFound = true;


        boolean foundPath = false;

        //TODO направи проверката за дали има вече съществуващи парчета
        for (int j = 0; j < 4; j++) {//четирите позиции позиции

            for (int i = 19; i > 2; i--) { //всеки ред
                boolean noZeroAppear = true;

                for (int k = 0; k < 10; k++) {//всеки квадрат

                    //TODO по някое време го направи че да може в дясно само четворката да се слага
                    boolean bound = true;

                    for (Point p : blockPoints) {
                        if (p.x >= game.MAP_WIDTH) {
                            bound = false;
                            break;
                        }
                    }

                    if (bound)
                        for (Point p : blockPoints) {
                            if (game.map[p.x][p.y] != 0) { //TODO OutOfBoundsExc
                                bound = false;
                                break;
                            }
                        }

                    //TODO проверени са парчетата и да види позиция ако не да мине през петте позиции и отново на горния ред и когато открие място
                    // то ще е най - долното а и свободно
                    // след това открива с булевата матрицата пътя, но ще е сложно с това че има няколко режима на движение


                    //TODO това е кода за сваляне при наличие на празни блокчета изцяло
                    if (bound) {
                        //проверка да провери да няма нули под всичките блокчета
                        //TODO наебана проверка за празни блокчета под илюционното парчето

                        //трябва да провери под всяко блокче какво има
                        // ако има вече съществуващо блокче тогава добре
                        // ако е от илюзионното парче да продължи
                        // ако има въздух под четирите да слезе надолу цялото
                        //TODO да провери и за други блокчета
                        int spacing = 0; //TODO много е грешно стига до 16
                        int height = 0;
                        int maxHeight = 0;

                        if (bound)
                            for (Point p : blockPoints) {
                                if (p.y + 1 < 20) {
                                    boolean passZero = false;
//TODO то проверява за всяка точка само първата от четирите и за парче 3 просто е било нагодено да стане трябва да има проверка
// за всички блокчета спрямо блокче и след това game.map[][] проверка

                                    for (Point p1 : blockPoints) {
                                        if (p.y + 1 == p1.y && p.x == p1.x) {
                                            passZero = true;
                                            break;
                                        }
                                    }

                                    if (!passZero)
                                        if (game.map[p.x][p.y + 1] == 0) {
                                            spacing++;
                                            break;
                                        }
                                }
                            }

                        //TODO всяко парче след първото не може да стигне до spacing = 0
                        // много е възможно да са с едно вдигнати във въздуха като се създават, а и като се завъртат


                        if (spacing == 0) {
                            //TODO какво е тази проверка
                            //TODO трябва да провери ако под всяко блокче има друго блокче или край на матрицата


                            double averageOfHeight = 0;
                            for (Point p : blockPoints) {
                                if (p.y > maxHeight)
                                    maxHeight = p.y;
                                height += p.y;

                            }
                            averageOfHeight = height / 4.0;
                            int cascade = 0;

                            //проверката дали под едно блокче има друго или край на матрицата
                            for (Point p : blockPoints) {
                                if (p.y + 1 < 20) {
                                    boolean round = true;
                                    for (Point p1 : blockPoints) {
                                        if (p.x == p1.x && p.y + 1 == p1.y) {
                                            round = false;
                                            break;
                                        }
                                    }
                                    if (round)
                                    if (game.map[p.x][p.y + 1] != 0) {
                                        cascade++;
                                    }
                                } else {
                                    cascade++;
                                }
                            }

                            //TODO трябва ми формула която да вземе и двете но с повече тежест да е височината

                            //TODO първо да вземе макс височина след това обща височина и след това колко е полегнало
//                            if ((maxHeight > maxTemp) || (height > heightTemp && cascade > cascadeTemp)) {
                            if ((maxHeight > maxTemp || height > heightTemp)  &&( cascade > cascadeTemp)) {
//                            if ((maxHeight > maxTemp && cascade > cascadeTemp) || (height > heightTemp )) {
                                heightTemp = height; //TODO когато имам този ред второто парче излиза от матрицата
                                maxTemp = maxHeight;
                                turnsTemp = j;
                                cascadeTemp = cascade;
                                positionNotFound = false;
                                System.arraycopy(blockPoints, 0, blockPointsTemp, 0, 4);

                                globalBegX = blockPoints[1].x;
                                globalBegY = blockPoints[1].y;
                            }
                        }
                        spacing = 0;
                        //
//TODO
                    }
                    for (Point p : blockPoints) {
                        p.x++;
                    }
                }

                //TODO tuka 2 krag
                //TODO провери кода какво трябва да прави
                //TODO ot tuk
                boolean lower = false;
                for (Point p : blockPoints) {
                    if (p.y >= 0) {
                        lower = true;
                        break;
                    }
                }

                if (lower)
                    for (Point p : blockPoints) {
                        p.y--;
                    }

                boolean shouldSlip = true;
                for (Point p : blockPoints) {
                    if (p.x == 0) {
                        shouldSlip = false;
                        break;
                    }
                }

                if (shouldSlip) {
                    boolean allSlipped;
                    do {
                        allSlipped = false;
                        for (Point p : blockPoints) {
                            p.x--;
                        }

                        for (Point p : blockPoints) {
                            if (p.x == 0) {
                                allSlipped = true;
                                break;
                            }
                        }
                    } while (!allSlipped);
                }

                //TODO do tuk
            }
            //TODO treti krag
            //TODO зануляване на всяко парче

            rotationLeft();

            boolean shouldSlip = true;
            for (Point p : blockPoints) {
                if (p.x == 0) {
                    shouldSlip = false;
                    break;
                }
            }

            if (shouldSlip) {
                boolean allSlipped;
                do {
                    allSlipped = false;
                    for (Point p : blockPoints) {
                        p.x--;
                    }

                    for (Point p : blockPoints) {
                        if (p.x == 0) {
                            allSlipped = true;
                            break;
                        }
                    }
                } while (!allSlipped);
            }


            //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA


            boolean shouldSneak = true;
            for (Point p : blockPoints) {
                if (p.y == 20) {
                    shouldSneak = false;
                    break;
                }
            }

            if (shouldSneak) {
                boolean allSneaked;
                do {
                    allSneaked = false;
                    for (Point p : blockPoints) {
                        p.y++;
                    }

                    for (Point p : blockPoints) {
                        if (p.y == 20) {
                            allSneaked = true;
                            break;
                        }
                    }
                } while (!allSneaked);
            }


            //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA


            boolean shouldJump = false;
            for (Point p : blockPoints) {
                if (p.y >= 20) {
                    shouldJump = true;
                    break;
                }
            }

            if (shouldJump) {
                boolean allJumped;
                do {
                    allJumped = true;
                    for (Point p : blockPoints) {
                        p.y--;
                    }

                    for (Point p : blockPoints) {
                        if (p.y >= 20) {
                            allJumped = false;
                            break;
                        }
                    }
                } while (!allJumped);
            }


            //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA


            //TODO възможен проблем но сега прави недостижимо позиция 0
            boolean shouldSide = false;
            for (Point p : blockPoints) {
                if (p.x == -1) {
                    shouldSide = true;
                    break;
                }
            }

            if (shouldSide) {
                boolean allSided;
                do {
                    allSided = true;
                    for (Point p : blockPoints) {
                        p.x++;
                    }

                    for (Point p : blockPoints) {
                        if (p.x == -1) {
                            allSided = false;
                            break;
                        }
                    }
                } while (!allSided);
            }

            //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
//            }

            //TODO tuka treti krag
//            System.out.println("WARTI WARTI");
        }

        temporalLobe++;

        //TODO проблемът не е в итерацията за щото правилно открива координатите

        //първо парче
        //[2, 19]
        //[1, 19]
        //[0, 19]
        //[2, 18]

        //второ парче
        //[5, 19]
        //[4, 19]
        //[3, 19]
        //[3, 18]

        //проблемът е в откриването на пътя и/или прилагането на пътя

        System.out.println("FIND PATH");

        //TODO ако няма открит път тогава да извика метода за открива на втора оптимална позиция

        if (positionNotFound) {
            //TODO код за ВОП

            secondLine();

//        } else {
        }
        findPath();
        //

        cascadeTemp = 0;
        actions();

//        game.switching();
    }

    private void actions() {
        //0 надолу 1 наляво 2 надясно 3 върти ляво 4 върти дясно

        //TODO трябва слизането да стане докато не стигне начална позиция тоест докато всич


        //TODO проблем изображение 1 - лилавото парче е изместено с едно наляво     N7
        //TODO изоб 2 - жълтото парче е 180 градуса завъртяно грешно                N8
        //TODO изоб 3 - синьото парче след придвижване остава едно във въздуха      N9
        // (не е толкова голям проблем)
        //TODO изоб 4 - аква след проблема с '5проблем' излиза извън матрицата      N12
        // макар че има валидни координати което води до
        // проблем с придвижването, а и е възможно да има също проблем с оставане във въздуха, но може да е от проблем от изчезването '5 проблем' на редове
        //TODO изоб 5 - лилавото парче е изместено с едно наляво както при проблем 1 N13
        //TODO изоб 6 - жълтото парче пак грешно както проблем 2 N17
        //TODO изоб 7 - може да има оптимизация (жълтото в този случаи)              N18
        // парчето да не гледа само да е "по - легнало" а и да гледа височина
        //TODO изоб 8 - трябва да гледа височината на парчето, но не става           N19
        //TODO изоб 9 - трябва да гледа височината червеното парче но не става       N20
        //TODO 99 проблем когато маха един или повече редове всичко изчезва а не само това което трябва


        switch (game.currentBlock.getShape()) {
            case 1:
            case 6:
            case 3:
            case 2:
            case 5:
                do {
                    game.currentBlock.automaticMoveDown();
                } while (game.currentBlock.getY(1) < 0);
                break;
            case 4:
            case 7:
                do {
                    game.currentBlock.automaticMoveDown();
                } while (game.currentBlock.getY(1) < 1);
                break;
        }

        for (int i = 0; i < turnsTemp; i++) {
            game.currentBlock.automaticRotationLeft();
        }

        for (int a : tempActions) {
            boolean poor = true;
            for (Point p : game.currentBlock.getCurrent()) {
                if (p.y == game.MAP_HEIGHT) {
                    poor = false;
                    break;
                }
            }

            if (poor)
                switch (a) {
                    case 2:
                        game.currentBlock.automaticMoveDown();
                        break;
                    case 1:
                        game.currentBlock.automaticMoveLeft();
                        break;
                    case 0:
                        game.currentBlock.automaticMoveRight();
                        break;
                    case 3:
                    case 4:
                        break;
                }
        }
    }

    private void resetMatrix() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                grid[i][j] = false;
            }
        }
    }

    private void createPoints() {
        switch (game.currentBlock.getShape()) {
            case 1:
                blockPoints[0] = new Point(0, 18);

                blockPoints[1] = new Point(1, 18);

                blockPoints[2] = new Point(2, 18);

                blockPoints[3] = new Point(2, 19);
                break;
            case 2:
                blockPoints[0] = new Point(0, 18);

                blockPoints[1] = new Point(1, 18);

                blockPoints[2] = new Point(2, 18);

                blockPoints[3] = new Point(1, 19);
                break;
            case 3:
                blockPoints[0] = new Point(0, 18);

                blockPoints[1] = new Point(1, 18);

                blockPoints[2] = new Point(2, 18);

                blockPoints[3] = new Point(0, 19);
                break;
            case 4:
                blockPoints[0] = new Point(0, 19);

                blockPoints[1] = new Point(1, 19);

                blockPoints[2] = new Point(1, 18);

                blockPoints[3] = new Point(2, 18);
                break;
            case 5:
                blockPoints[0] = new Point(0, 19);

                blockPoints[1] = new Point(1, 19);

                blockPoints[2] = new Point(2, 19);

                blockPoints[3] = new Point(3, 19);
                break;
            case 6:
                blockPoints[0] = new Point(0, 18);

                blockPoints[1] = new Point(1, 18);

                blockPoints[2] = new Point(0, 19);

                blockPoints[3] = new Point(1, 19);

                break;
            case 7:
                blockPoints[0] = new Point(0, 18);

                blockPoints[1] = new Point(1, 19);

                blockPoints[2] = new Point(1, 18);

                blockPoints[3] = new Point(2, 19);
                break;
        }

        tempBlockArrayInit = false;
    }

    private void rotationLeft() {
        if (game.currentBlock.getShape() != 6) {
            for (int i = 0; i < 4; i++) {
                if (i != 1) {
                    int currentRotatorX = blockPoints[i].x - blockPoints[1].x;
                    int currentRotatorY = blockPoints[i].y - blockPoints[1].y;

                    int tempX = (int) Math.round(currentRotatorX * Math.cos(1.5707963267948966) - currentRotatorY * Math.sin(1.5707963267948966));
                    int tempY = (int) Math.round(currentRotatorX * Math.sin(1.5707963267948966) - currentRotatorY * Math.cos(1.5707963267948966));
//
//                    if (tempX + blockPoints[1].x < 0 || tempX + blockPoints[1].x > game.MAP_WIDTH - 1)
//                        return;
//                    if (tempY + blockPoints[1].y < 0 || tempY + blockPoints[1].y > game.MAP_HEIGHT - 1)
//                        return;
//                    if (game.map[tempX + blockPoints[1].x][tempY + blockPoints[1].y] != 0)
//                        return;
                }
            }
            for (int i = 0; i < 4; i++) {
                if (i != 1) {
                    int currentRotatorX = blockPoints[i].x - blockPoints[1].x;
                    int currentRotatorY = blockPoints[i].y - blockPoints[1].y;

                    int tempX = (int) Math.round(currentRotatorX * Math.cos(1.5707963267948966) - currentRotatorY * Math.sin(1.5707963267948966));
                    int tempY = (int) Math.round(currentRotatorX * Math.sin(1.5707963267948966) - currentRotatorY * Math.cos(1.5707963267948966));

                    blockPoints[i].x = tempX + blockPoints[1].x;
                    blockPoints[i].y = tempY + blockPoints[1].y;
                }
            }
//                game.render();
        }
    }

    private void rotationRight() {
        if (game.currentBlock.getShape() != 6) {
            for (int i = 0; i < 4; i++) {
                if (i != 1) {
                    int currentRotatorX = blockPoints[i].x - blockPoints[1].x;
                    int currentRotatorY = blockPoints[i].y - blockPoints[1].y;

                    int tempX = (int) Math.round(currentRotatorX * Math.cos(1.5707963267948966) - currentRotatorY * Math.sin(1.5707963267948966));
                    int tempY = (int) Math.round(currentRotatorX * Math.sin(1.5707963267948966) - currentRotatorY * Math.cos(1.5707963267948966));

//                    if (tempX + blockPoints[1].x < 0 || tempX + blockPoints[1].x > game.MAP_WIDTH - 1)
//                        return;
//                    if (tempY + blockPoints[1].y < 0 || tempY + blockPoints[1].y > game.MAP_HEIGHT - 1)
//                        return;
//                    if (game.map[tempX + blockPoints[1].x][tempY + blockPoints[1].y] != 0)
//                        return;
                }
            }
            for (int i = 0; i < 4; i++) {
                if (i != 1) {
                    int currentRotatorX = blockPoints[i].x - blockPoints[1].x;
                    int currentRotatorY = blockPoints[i].y - blockPoints[1].y;

                    int tempX = (int) Math.round(currentRotatorY * Math.sin(1.5707963267948966) - currentRotatorX * Math.cos(1.5707963267948966));
                    int tempY = (int) Math.round(currentRotatorY * Math.cos(1.5707963267948966) - currentRotatorX * Math.sin(1.5707963267948966));

                    blockPoints[i].x = tempX + blockPoints[1].x;
                    blockPoints[i].y = tempY + blockPoints[1].y;
                }
            }
//                game.render();
        }
    }

    private void findPath() {
        switch (game.currentBlock.getShape()) {
            case 1:
            case 6:
            case 5:
            case 3:
            case 2:
                endX = 5;
                endY = 0;
                break;
            case 4:
            case 7:
                endX = 5; //TODO може да трябва да не е 4 а 5
                endY = 1;
                break;

        }
//        recursiveFinder(begX, begY);
        recursiveFinder(globalBegX, globalBegY);
    }

    private void recursiveFinder(int posX, int posY) {
        // подава на околните четири квадрата

        if (posX == endX && posY == endY) {
//            tempActions.add(new Point(posX, posY));
        } else
            recurseCaller(posX, posY);
    }

    private void recurseCaller(int posX, int posY) { //TODO някъде логиката за когато стигне мястото
        int medX = endX - posX;
        int medY = endY - posY;

        if (medX > 0) {
            posX++;
            tempActions.add(1);
        } else if (medX < 0) {
            posX--;
            tempActions.add(0);
        } else if (medY > 0) {
            posY++;
        } else if (medY < 0) {
            posY--;
            tempActions.add(2);
        }

        recursiveFinder(posX, posY);
    }

    private void secondLine() {
        actions = new ArrayList<>();
        tempActions = new ArrayList<>();

        System.out.println("Created points");
        createPoints();
        turns = 0;
        turnsTemp = 0;
        heightTemp = 0;
        spacingTemp = 5;

        boolean foundPath = false;

        for (int j = 0; j < 4; j++) {//четирите позиции позиции

            for (int i = 19; i > 2; i--) { //всеки ред
                boolean noZeroAppear = true;

                for (int k = 0; k < 10; k++) {//всеки квадрат
                    boolean bound = true;

                    for (Point p : blockPoints) {
                        if (p.x >= game.MAP_WIDTH) {
                            bound = false;
                            break;
                        }
                    }

                    if (bound)
                        for (Point p : blockPoints) {
                            if (game.map[p.x][p.y] != 0) {
                                bound = false;
                                break;
                            }
                        }
                    if (bound) {
                        int spacing = 0; //TODO много е грешно стига до 16
                        int height = 0;
                        int maxHeight = 0;

                        if (bound) {

                            //TODO за всеки блок да провери под себе си за други от парчето и за въздушни блокчета до края на матрицата или поява на друго парче
                            // ако срещне от себе си да continue при въздух число++  - - - това да прави , докато следващото отдолу не е извън матрицата или друго парче
                        }
                        int tempY;
                        for (Point p : blockPoints) {
                            tempY = p.y + 1;

                            while (tempY < 20) {
                                boolean match = false;
                                for (Point p1 : blockPoints) {
                                    if (tempY == p1.y && p.x == p1.x) {
                                        match = true;
                                        break;
                                    }
                                }

                                if (match)
                                    break;

                                if (!match) {
                                    if (game.map[p.x][tempY] == 0)
                                        spacing++;
                                }

                                tempY++;
                            }
                        }

//                        System.out.println(spacing + "\t" + spacingTemp + "Z345AF");

                        //TODO да има и проверка за височина ама не абсолютна а само тежест
                        if (spacing < spacingTemp) {
                            //TODO тук доразгледай че може не открива пътя и остава без нищо

                            heightTemp = height; //TODO когато имам този ред второто парче излиза от матрицата
                            maxTemp = maxHeight;
                            turnsTemp = j;
                            positionNotFound = false;
                            System.arraycopy(blockPoints, 0, blockPointsTemp, 0, 4);

                            globalBegX = blockPoints[1].x;
                            globalBegY = blockPoints[1].y;
                            System.out.println("Wlezna   " + blockPoints[1].x + " " + blockPoints[1].y + " " + spacing + " " + spacingTemp);
                            System.out.println(j + "  " + blockPoints[0].x + "  " + blockPoints[0].y);
                            System.out.println(Arrays.toString(blockPoints));
                            System.out.println(turnsTemp);
                            spacingTemp = spacing;

                            spacing = 0;
                        }
                        for (Point p : blockPoints) {
                            p.x++;
                        }
                    }
//                    boolean lower = false;
//                    for (Point p : blockPoints) {
//                        if (p.y >= 0) {
//                            lower = true;
//                            break;
//                        }
//                    }
//
//                    if (lower)
//                        for (Point p : blockPoints) {
//                            p.y--;
//                        }
//
//                    boolean shouldSlip = true;
//                    for (Point p : blockPoints) {
//                        if (p.x == 0) {
//                            shouldSlip = false;
//                            break;
//                        }
//                    }
//
//                    if (shouldSlip) {
//                        boolean allSlipped;
//                        do {
//                            allSlipped = false;
//                            for (Point p : blockPoints) {
//                                p.x--;
//                            }
//
//                            for (Point p : blockPoints) {
//                                if (p.x == 0) {
//                                    allSlipped = true;
//                                    break;
//                                }
//                            }
//                        } while (!allSlipped);
//                    }
                }
                //TODO parwi krag
                //TODO ot tuk
                boolean lower = false;
                for (Point p : blockPoints) {
                    if (p.y >= 0) {
                        lower = true;
                        break;
                    }
                }

                if (lower)
                    for (Point p : blockPoints) {
                        p.y--;
                    }

                boolean shouldSlip = true;
                for (Point p : blockPoints) {
                    if (p.x == 0) {
                        shouldSlip = false;
                        break;
                    }
                }

                if (shouldSlip) {
                    boolean allSlipped;
                    do {
                        allSlipped = false;
                        for (Point p : blockPoints) {
                            p.x--;
                        }

                        for (Point p : blockPoints) {
                            if (p.x == 0) {
                                allSlipped = true;
                                break;
                            }
                        }
                    } while (!allSlipped);
                }

//                TODO do tuk
//                if (game.currentBlock.getShape() == 7)
//                System.out.println("Ako sme tuk ne sme");
//
//                rotationLeft();
//
//                boolean shouldSlip = true;
//                for (Point p : blockPoints) {
//                    if (p.x == 0) {
//                        shouldSlip = false;
//                        break;
//                    }
//                }
//
//                if (shouldSlip) {
//                    boolean allSlipped;
//                    do {
//                        allSlipped = false;
//                        for (Point p : blockPoints) {
//                            p.x--;
//                        }
//
//                        for (Point p : blockPoints) {
//                            if (p.x == 0) {
//                                allSlipped = true;
//                                break;
//                            }
//                        }
//                    } while (!allSlipped);
//                }
//
//
//                //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
//
//
//                boolean shouldSneak = true;
//                for (Point p : blockPoints) {
//                    if (p.y == 20) {
//                        shouldSneak = false;
//                        break;
//                    }
//                }
//
//                if (shouldSneak) {
//                    boolean allSneaked;
//                    do {
//                        allSneaked = false;
//                        for (Point p : blockPoints) {
//                            p.y++;
//                        }
//
//                        for (Point p : blockPoints) {
//                            if (p.y == 20) {
//                                allSneaked = true;
//                                break;
//                            }
//                        }
//                    } while (!allSneaked);
//                }
//
//
//                //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
//
//
//                boolean shouldJump = false;
//                for (Point p : blockPoints) {
//                    if (p.y >= 20) {
//                        shouldJump = true;
//                        break;
//                    }
//                }
//
//                if (shouldJump) {
//                    boolean allJumped;
//                    do {
//                        allJumped = true;
//                        for (Point p : blockPoints) {
//                            p.y--;
//                        }
//
//                        for (Point p : blockPoints) {
//                            if (p.y >= 20) {
//                                allJumped = false;
//                                break;
//                            }
//                        }
//                    } while (!allJumped);
//                }
//
//
//                //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
//
//
//                boolean shouldSide = false;
//                for (Point p : blockPoints) {
//                    if (p.x == -1) {
//                        shouldSide = true;
//                        break;
//                    }
//                }
//
//                if (shouldSide) {
//                    boolean allSided;
//                    do {
//                        allSided = true;
//                        for (Point p : blockPoints) {
//                            p.x++;
//                        }
//
//                        for (Point p : blockPoints) {
//                            if (p.x == -1) {
//                                allSided = false;
//                                break;
//                            }
//                        }
//                    } while (!allSided);
//                }
            }
            //TODO wtori krag

            rotationLeft();

            boolean shouldSlip = true;
            for (Point p : blockPoints) {
                if (p.x == 0) {
                    shouldSlip = false;
                    break;
                }
            }

            if (shouldSlip) {
                boolean allSlipped;
                do {
                    allSlipped = false;
                    for (Point p : blockPoints) {
                        p.x--;
                    }

                    for (Point p : blockPoints) {
                        if (p.x == 0) {
                            allSlipped = true;
                            break;
                        }
                    }
                } while (!allSlipped);
            }


            //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA


            boolean shouldSneak = true;
            for (Point p : blockPoints) {
                if (p.y == 20) {
                    shouldSneak = false;
                    break;
                }
            }

            if (shouldSneak) {
                boolean allSneaked;
                do {
                    allSneaked = false;
                    for (Point p : blockPoints) {
                        p.y++;
                    }

                    for (Point p : blockPoints) {
                        if (p.y == 20) {
                            allSneaked = true;
                            break;
                        }
                    }
                } while (!allSneaked);
            }


            //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA


            boolean shouldJump = false;
            for (Point p : blockPoints) {
                if (p.y >= 20) {
                    shouldJump = true;
                    break;
                }
            }

            if (shouldJump) {
                boolean allJumped;
                do {
                    allJumped = true;
                    for (Point p : blockPoints) {
                        p.y--;
                    }

                    for (Point p : blockPoints) {
                        if (p.y >= 20) {
                            allJumped = false;
                            break;
                        }
                    }
                } while (!allJumped);
            }


            //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA


            //TODO възможен проблем но сега прави недостижимо позиция 0
            boolean shouldSide = false;
            for (Point p : blockPoints) {
                if (p.x == -1) {
                    shouldSide = true;
                    break;
                }
            }

            if (shouldSide) {
                boolean allSided;
                do {
                    allSided = true;
                    for (Point p : blockPoints) {
                        p.x++;
                    }

                    for (Point p : blockPoints) {
                        if (p.x == -1) {
                            allSided = false;
                            break;
                        }
                    }
                } while (!allSided);
            }

            //TODO tuka treti krag


        }
    }
}
