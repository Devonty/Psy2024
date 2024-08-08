package GameParts.FieldParts;

import CollideModel.CollideObject;
import CollideModel.Movable.CircleModel;
import CollideModel.Movable.MovableObject;
import CollideModel.NotMovable.NotMovableObject;
import ModelDrawer.ObjectDrawer;
import MyMath.Point2d;
import MyUtilz.FieldArrayList;

import java.util.*;

public class Field {
    protected List<ObjectDrawer> objectDrawers;
    protected List<CollideObject> allObjects;
    protected List<MovableObject> movableObjects;
    protected List<NotMovableObject> notMovableObjects;

    private final List<List<List<CollideObject>>> fieldMatrix;
    private double minX, minY, maxX, maxY;
    private int height = 0, width = 0;
    private double deltaStep;

    public Field() {
        this.fieldMatrix = new FieldArrayList<>();
        this.objectDrawers = new ArrayList<>();
        this.allObjects = new ArrayList<>();
        this.movableObjects = new ArrayList<>();
        this.notMovableObjects = new ArrayList<>();

    }

    protected void add(ObjectDrawer toAdd) {
        objectDrawers.add(toAdd);
        allObjects.add(toAdd.getCollideModel());
        if (toAdd.getCollideModel() instanceof MovableObject)
            movableObjects.add((MovableObject) toAdd.getCollideModel());
        if (toAdd.getCollideModel() instanceof NotMovableObject)
            notMovableObjects.add((NotMovableObject) toAdd.getCollideModel());
    }

    private static final int[] Is = new int[]{-1, 0, 1, -1, 0, 1, -1, 0, 1};
    private static final int[] Js = new int[]{-1, -1, -1, 0, 0, 0, 1, 1, 1};

    public List<CollideObject> getNeighborList(CollideObject collideObject) {
        List<CollideObject> neighbors = new LinkedList<>();
        int[] ij = getIJbyCollideObject(collideObject);
        for (int i = 0; i < Is.length; i++) {
            int indexI = ij[0] + Is[i];
            int indexJ = ij[1] + Js[i];
            neighbors.addAll(getFromFieldMask(indexI, indexJ));
        }
        neighbors.remove(collideObject);
        return neighbors;
    }

    private int[] getIJbyCollideObject(CollideObject collideObject) {
        int[] ij = new int[2];
        ij[0] = (int) ((collideObject.x() - minX) / deltaStep);
        ij[1] = (int) ((collideObject.y() - minY) / deltaStep);
        return ij;
    }

    private static final List<CollideObject> emptyList = new ArrayList<>(0);

    private List<CollideObject> getFromFieldMask(int i, int j) {
        if (i < 0 || j < 0 || i >= fieldMatrix.size() || j >= fieldMatrix.get(i).size()) return emptyList;
        return fieldMatrix.get(i).get(j);
    }

    public void makeProjection() {
        fieldMatrix.clear();
        calcParams();
        for (CollideObject collideObject : allObjects) {
            int[] ij = getIJbyCollideObject(collideObject);
            getFromFieldMask(ij[0], ij[1]).add(collideObject);
        }
    }


    public boolean calcParams() {
        if (allObjects.isEmpty()) return false;
        resetParams();
        allObjects.forEach(this::updateParams);
        clearFieldMatrix();
        extendFieldMatrix();
        return true;
    }

    private void extendFieldMatrix() {
        height = Math.max((int) ((maxY - minY) / deltaStep + 1), height);
        width = Math.max((int) ((maxX - minX) / deltaStep + 1), width);

        while (fieldMatrix.size() <= height) {
            List<List<CollideObject>> list = new FieldArrayList<>(width);

            while (list.size() <= width) {
                list.add(new LinkedList<>());
            }
            fieldMatrix.add(list);
        }
    }

    public Point2d getStartPoint() {
        return new Point2d(minX, minY);
    }

    private void clearFieldMatrix() {
        for (List<List<CollideObject>> row : fieldMatrix) {
            for (List<CollideObject> collideObjects : row) {
                collideObjects.clear();
            }
        }
    }

    public void resetParams() {
        minX = Double.MAX_VALUE;
        maxX = -Double.MAX_VALUE;
        minY = Double.MAX_VALUE;
        maxY = -Double.MAX_VALUE;

        deltaStep = 1;
    }

    private void updateParams(CollideObject collideObject) {
        minX = Math.min(minX, collideObject.x());
        maxX = Math.max(maxX, collideObject.x());
        minY = Math.min(minY, collideObject.y());
        maxY = Math.max(maxY, collideObject.y());

        if (collideObject instanceof CircleModel) {
            deltaStep = Math.max(deltaStep, ((CircleModel) collideObject).radius() * 2.1);
        }

    }

    protected ObjectDrawer get(int index) {
        return objectDrawers.get(index);
    }

    public int count() {
        return objectDrawers.size();
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public double getDeltaStep() {
        return deltaStep;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
