package Components.CollideComponent;

import CollideModel.GameObject;
import CollideModel.Movable.CircleModel;
import CollideModel.Movable.MovableObject;
import CollideModel.NotMovable.NotMovableObject;
import Components.BaseComponent;
import MyMath.Point2d;
import MyUtilz.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class CollideComponent extends BaseComponent<GameObject> {
    private static final double DEFAULT_DELTA_STEP = 10d;
    private final List<MovableObject> movableObjects;
    private final List<NotMovableObject> notMovableObjects;

    private Cell[][] fieldMatrix;

    private double minX, minY, maxX, maxY;
    private int height, width;
    private double deltaStep;

    public CollideComponent(Function<GameObject, Boolean> addingRule) {
        super(addingRule);
        this.height = 0;
        this.width = 0;
        this.fieldMatrix = new Cell[0][0];

        this.movableObjects = new ArrayList<>();
        this.notMovableObjects = new ArrayList<>();
    }

    public CollideComponent() {
        this(DEFAULT_ADDING_RULE);
    }

    @Override
    public void subAdd(GameObject toAdd) {
        allObjects.add(toAdd);
        if (toAdd instanceof MovableObject)
            movableObjects.add((MovableObject) toAdd);
        if (toAdd instanceof NotMovableObject)
            notMovableObjects.add((NotMovableObject) toAdd);
    }

    @Override
    public void update() {
        //makeProjection();
        //movableObjects.forEach(this::collideWithNeighbors);
        CollideController.collide(movableObjects);
        CollideController.collide(movableObjects, notMovableObjects);
    }

    private void collideWithNeighbors(GameObject gameObject){
        CollideController.collide(gameObject, getNeighborList(gameObject));
    }

    private static final int[] Is = new int[]{-1, 0, 1, -1, 0, 1, -1, 0, 1};
    private static final int[] Js = new int[]{-1, -1, -1, 0, 0, 0, 1, 1, 1};

    public List<GameObject> getNeighborList(GameObject gameObject) {
        List<GameObject> neighbors = new LinkedList<>();
        int[] ij = getIJbyCollideObject(gameObject);
        for (int i = 0; i < Is.length; i++) {
            int indexI = ij[0] + Is[i];
            int indexJ = ij[1] + Js[i];
            neighbors.addAll(getFromFieldMask(indexI, indexJ));
        }
        neighbors.remove(gameObject);
        return neighbors;
    }

    private int[] getIJbyCollideObject(GameObject gameObject) {
        int[] ij = new int[2];
        ij[0] = (int) ((gameObject.x() - minX) / deltaStep);
        ij[1] = (int) ((gameObject.y() - minY) / deltaStep);
        return ij;
    }

    private static final List<GameObject> emptyList = new ArrayList<>(0);
    private List<GameObject> getFromFieldMask(int i, int j) {
        if (i < 0 || j < 0 || i >= fieldMatrix.length || j >= fieldMatrix[i].length) return emptyList;
        return fieldMatrix[i][j].getList();
    }

    public void makeProjection() {
        clearFieldMatrix();
        calcParams();
        for (GameObject gameObject : allObjects) {
            int[] ij = getIJbyCollideObject(gameObject);
            getFromFieldMask(ij[0], ij[1]).add(gameObject);
        }
    }

    public void calcParams() {
        if (allObjects.isEmpty()) return;
        resetParams();
        allObjects.forEach(this::updateParams);
        clearFieldMatrix();
        extendFieldMatrix();
    }

    private void extendFieldMatrix() {
        int oldHeight = height;
        int oldWidth = width;

        height = Math.max((int) ((maxY - minY) / deltaStep + 1), height);
        width = Math.max((int) ((maxX - minX) / deltaStep + 1), width);

        if(oldHeight == height && oldWidth== width) return;

        fieldMatrix = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                fieldMatrix[i][j] = new Cell();
            }
        }
    }
    public Point2d getStartPoint() {
        return new Point2d(minX, minY);
    }

    private void clearFieldMatrix() {
        Arrays.stream(fieldMatrix).forEach(row -> Arrays.stream(row).forEach(Cell::clear));
    }

    public void resetParams() {
        minX = Double.MAX_VALUE;
        maxX = -Double.MAX_VALUE;
        minY = Double.MAX_VALUE;
        maxY = -Double.MAX_VALUE;


        deltaStep = DEFAULT_DELTA_STEP;
    }

    private void updateParams(GameObject gameObject) {
        minX = Math.min(minX, gameObject.x());
        maxX = Math.max(maxX, gameObject.x());
        minY = Math.min(minY, gameObject.y());
        maxY = Math.max(maxY, gameObject.y());

        if (gameObject instanceof CircleModel) {
            deltaStep = Math.max(deltaStep, ((CircleModel) gameObject).radius() * 2.1);
        }

    }
}
