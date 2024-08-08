package GameParts.FieldParts;

import CollideModel.CollideObject;
import CollideModel.Movable.CircleModel;
import CollideModel.Movable.MovableObject;
import CollideModel.NotMovable.NotMovableObject;
import ModelDrawer.ObjectDrawer;

import java.util.*;

public class Field {
    protected List<ObjectDrawer> objectDrawers;
    protected List<CollideObject> allObjects;
    protected List<MovableObject> movableObjects;
    protected List<NotMovableObject> notMovableObjects;

    private Map<Integer, Map<Integer, List<CollideObject>>> fieldMask;
    private double minX, minY, maxX, maxY;
    private double deltaStepX, deltaStepY;
    private double deltaStep;

    public Field() {
        this.fieldMask = new HashMap<>();
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
        ij[0] = (int) ((collideObject.x() - minX) / deltaStepX);
        ij[1] = (int) ((collideObject.y() - minY) / deltaStepY);
        return ij;
    }

    private List<CollideObject> getFromFieldMask(int i, int j) {
        fieldMask.putIfAbsent(i, new HashMap<>());
        fieldMask.get(i).putIfAbsent(j, new LinkedList<>());
        return fieldMask.get(i).get(j);
    }

    public void calcFieldMask() {
        fieldMask.clear();
        if (!calcParams()) return;

        deltaStepX =  deltaStep;
        deltaStepY =  deltaStep;

        makeProjection();
    }

    public void makeProjection(){
        fieldMask.clear();
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
        return true;
    }

    public void resetParams(){
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

        if(collideObject instanceof CircleModel) {
            deltaStep = Math.max(deltaStep, ((CircleModel) collideObject).radius() * 2.1);
        }

    }

    protected ObjectDrawer get(int index) {
        return objectDrawers.get(index);
    }

    public int count() {
        return objectDrawers.size();
    }
}
