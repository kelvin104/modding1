package com.fyp1155125212.fypmod.entity.renderer;

import com.fyp1155125212.fypmod.entity.custom.NeutralCitizen_J;
import com.fyp1155125212.fypmod.entity.custom.Police;
import com.fyp1155125212.fypmod.entity.model.NeutralCitizen_JModel;
import com.fyp1155125212.fypmod.entity.model.PoliceModel;
import com.fyp1155125212.fypmod.fypMod;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class NeutralCitizen_JRenderer extends MobRenderer<NeutralCitizen_J,NeutralCitizen_JModel<NeutralCitizen_J>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(fypMod.MOD_ID,"textures/entity/modvillager/modvillager_n.png");

    public NeutralCitizen_JRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NeutralCitizen_JModel<>(0.0F, 0.0F, 64, 64), 0.5F);
        this.addLayer(new HeldItemLayer<NeutralCitizen_J, NeutralCitizen_JModel<NeutralCitizen_J>>(this) {
            public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, NeutralCitizen_J entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                if (entitylivingbaseIn.isAggressive()) {
                    super.render(matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
                }

            }
        });
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getEntityTexture(NeutralCitizen_J entity) {
        return TEXTURE;
    }
}