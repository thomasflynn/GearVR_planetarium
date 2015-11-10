/* Copyright 2015 Dmitry Brant
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dmitrybrant.gearvrf.planetarium;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.opengl.GLES20;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.gearvrf.GVRBitmapTexture;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMaterial;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.utility.Log;

public class Asterism {
    private static final String TAG = "Asterism";
    private static final float LABEL_WIDTH = 40f;
    private static final int LABEL_COLOR = 0xff003050;

    private static GVRMaterial asterismMaterial;

    private final String name;
    public String getName() {
        return name;
    }

    private List<AsterismNode> nodes = new ArrayList<>();
    public List<AsterismNode> getNodes() {
        return nodes;
    }

    public Asterism(String line) throws IOException {
        String[] lineArr = line.split("\\s+");
        name = Util.bayerToFullName(lineArr[0]);
        for (int i = 2; i < lineArr.length; i++) {
            int hipNum = Integer.parseInt(lineArr[i]);
            AsterismNode node = new AsterismNode(hipNum);
            nodes.add(node);
        }
    }

    public GVRSceneObject createSceneObject(GVRContext context) {
        if (asterismMaterial == null) {
            SolidColorShader shader = new SolidColorShader(context);
            asterismMaterial = new GVRMaterial(context, shader.getShaderId());
            asterismMaterial.setVec4(SolidColorShader.COLOR_KEY, 0.0f, 0.1f, 0.15f, 1.0f);
        }
        GVRMesh mesh = createMesh(context);
        GVRSceneObject asterismObj = new GVRSceneObject(context, mesh);
        asterismObj.getRenderData().setMaterial(asterismMaterial);
        asterismObj.getRenderData().setDepthTest(false);
        asterismObj.getRenderData().setDrawMode(GLES20.GL_LINES);
        return asterismObj;
    }

    public GVRSceneObject createLabelObject(GVRContext gvrContext, Context context) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(LABEL_COLOR);
        paint.setTextSize((int) (16 * context.getResources().getDisplayMetrics().density));
        Rect bounds = new Rect();
        paint.getTextBounds(name, 0, name.length(), bounds);

        Bitmap bmp = Bitmap.createBitmap(bounds.width(), bounds.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        canvas.drawText(name, 0, bounds.height() - 1, paint);

        float aspect = (float) bounds.width() / (float) bounds.height();
        GVRSceneObject sobj = new GVRSceneObject(gvrContext, gvrContext.createQuad(LABEL_WIDTH, LABEL_WIDTH / aspect), new GVRBitmapTexture(gvrContext, bmp));
        sobj.getRenderData().setDepthTest(false);
        return sobj;
    }

    public float getCenterRa() {
        double ra = 0.0;
        for (AsterismNode node : nodes) {
            ra += node.getStar().ra;
        }
        return (float) (ra / nodes.size());
    }

    public float getCenterDec() {
        double dec = 0.0;
        for (AsterismNode node : nodes) {
            dec += node.getStar().dec;
        }
        return (float) (dec / nodes.size());
    }

    private GVRMesh createMesh(GVRContext context) {
        float[] vertices = new float[nodes.size() * 3];
        char[] indices = new char[nodes.size()];
        int vertexPos = 0;
        GVRMesh mesh = new GVRMesh(context);
        for(AsterismNode node : nodes) {
            if (node.getStar() == null) {
                Log.w(TAG, "Orphan asterism node: " + node.getHipNum());
                continue;
            }
            vertices[vertexPos++] = node.getStar().sceneObj.getTransform().getPositionX();
            vertices[vertexPos++] = node.getStar().sceneObj.getTransform().getPositionY();
            vertices[vertexPos++] = node.getStar().sceneObj.getTransform().getPositionZ();
        }
        for (int i = 0; i < indices.length; i++) {
            indices[i] = (char) i;
        }
        mesh.setVertices(vertices);
        mesh.setIndices(indices);
        return mesh;
    }

    public class AsterismNode {
        private int hipNum;
        public int getHipNum() {
            return hipNum;
        }

        private SkyObject star;
        public SkyObject getStar() {
            return star;
        }
        public void setStar(SkyObject star) {
            this.star = star;
        }

        public AsterismNode(int hipNum) {
            this.hipNum = hipNum;
        }
    }
}


